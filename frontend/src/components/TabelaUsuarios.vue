<template>
    <div>
        <table>
            <thead>
                <tr>
                    <th id="nome-coluna">Nome</th>
                    <th>Senha</th>
                </tr>
            </thead>
            <tbody>
                <tr v-for="usuario in paginatedUsuarios" :key="usuario.id">
                    <td>{{ usuario.nome }}</td>
                    <td>{{ usuario.senha }}</td>
                </tr>
            </tbody>
        </table>

        <div v-if="usuarios.length > perPage" id="selectionPage-container">
            <h1 @click="updatePage(currentPage - 1)">&lt;</h1>
            <h1 id="page-label">{{currentPage}}</h1>
            <div @click="updatePage(currentPage + 1)"><h1>&gt;</h1> </div>
        </div>
    </div>
</template>

<script>
import { ref, computed, onMounted, onBeforeUnmount } from 'vue';
import Pagination from 'laravel-vue-pagination';
import api from "../services/axios";

export default {
    name: 'TabelaUsuarios',
    components: {
        Pagination
    },
    setup() {
        const usuarios = ref([]);
        const currentPage = ref(1);
        const perPage = ref(16);

        const fetchUsuarios = async () => {
            try {
                console.log(currentPage.value - 1,perPage.value)
                const response = await api.get('/user', {
                    params: {
                        page: currentPage.value - 1,
                        size: perPage.value
                    }
                });
                
                usuarios.value = response.data.content.map(user => ({
                    id: user.id,
                    nome: user.username,
                    senha: '******' // Placeholder for password
                }));
                console.log(usuarios.value);
            } catch (error) {
                console.error("Erro ao buscar usuários:", error);
            }
        };


        const handleUserRegistered = () => {
            fetchUsuarios();
        };

        onMounted(() => {
            fetchUsuarios();
            window.addEventListener('user-registered', handleUserRegistered);
        });

        onBeforeUnmount(() => {
            window.removeEventListener('user-registered', handleUserRegistered);
        });


        const paginatedUsuarios = computed(() => {
            const start = (currentPage.value - 1) * perPage.value;
            const end = start + perPage.value;
            return usuarios.value.slice(start, end);
        });

        const updatePage = (page) => {
            if (page >= 1 && page <= Math.ceil(usuarios.value.length / perPage.value)) {
                currentPage.value = page;
                fetchUsuarios();
            }
        };

        return {
            usuarios,
            currentPage,
            perPage,
            paginatedUsuarios,
            updatePage
        };
    }
};
</script>

<style scoped>
table {
    width: 100%;
    border-collapse: collapse;
}

th, td {
    border: 1px solid #ddd;
    padding: 8px;
}

th {
    background-color: #2888E4;
    color: white;
}

tr {
    background-color: #EDEDED;
}

tr:nth-child(even) {
    background-color: #E3E3E3;
}

#selectionPage-container {
    display: flex;
    margin-top: 10px;
}

#page-label {
    margin: 0 10px;
}

#nome-coluna {
    width: 65%;
}
</style>