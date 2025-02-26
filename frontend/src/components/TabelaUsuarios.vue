<template>
    <div>
        <table>
            <thead>
                <tr>
                    <th id="id-coluna">ID</th>
                    <th id="nome-coluna">Nome</th>
                    <th>Senha</th>
                </tr>
            </thead>
            <tbody>
                <tr v-for="usuario in usuarios" :key="usuario.id">
                    <td>{{ usuario.id }}</td>
                    <td>{{ usuario.nome }}</td>
                    <td>{{ usuario.senha }}</td>
                </tr>
            </tbody>
        </table>

        <div id="selectionPage-container">
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
    setup() {
        const usuarios = ref([]);
        const currentPage = ref(1);
        const perPage = ref(16);
        const usuariosMorePerPage = ref(false);

        const fetchUsuarios = async (page) => {
            try {
                const response = await api.get('/user', {
                    params: {
                        page: page - 1,
                        size: perPage.value
                    }
                });
                
                usuarios.value = response.data.content.map(user => ({
                    id: user.id,
                    nome: user.username,
                    senha: '******' // Placeholder for password
                }));

                currentPage.value = page;

            } catch (error) {
                console.error("Erro ao buscar usuários:", error);
            }
        };

        

        const handleUserRegistered = () => {
            fetchUsuarios(currentPage.value);
        };

        onMounted(() => {
            fetchUsuarios(currentPage.value);
            window.addEventListener('user-registered', handleUserRegistered);
        });

        onBeforeUnmount(() => {
            window.removeEventListener('user-registered', handleUserRegistered);
        });



        const updatePage = (page) => { {
            if (page > 0){          
                fetchUsuarios(page);
            }
        }};

        return {
            usuarios,
            currentPage,
            perPage,
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
#id-coluna {
    width: 6%;
}
</style>