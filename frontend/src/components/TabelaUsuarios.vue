

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
        <pagination
            :data="usuarios"
            :limit="16"
            @pagination-change-page="updatePage"
        ></pagination>
        <div v-if="usuarios.length > perPage" id="selectionPage-container">
            <h1 @click="updatePage(currentPage - 1)">&lt;</h1>
            <h1 id="page-label">{{currentPage}}</h1>
            <div @click="updatePage(currentPage + 1)"><h1>&gt;</h1> </div>
        </div>
    </div>
</template>

<script>
import { ref, computed } from 'vue';
import Pagination from 'laravel-vue-pagination';

export default {
    name: 'TabelaUsuarios',
    components: {
        Pagination
    },
    setup() {
        const usuarios = ref([
            { id: 1, nome: 'Usuario 1', senha: 'senha1' },
            { id: 2, nome: 'Usuario 2', senha: 'senha2' },
            { id: 3, nome: 'Usuario 3', senha: 'senha3' },
            { id: 4, nome: 'Usuario 4', senha: 'senha4' },
            { id: 5, nome: 'Usuario 5', senha: 'senha5' },
            { id: 6, nome: 'Usuario 6', senha: 'senha6' },
            { id: 7, nome: 'Usuario 7', senha: 'senha7' },
            { id: 8, nome: 'Usuario 8', senha: 'senha8' },
            { id: 9, nome: 'Usuario 9', senha: 'senha9' },
            { id: 10, nome: 'Usuario 10', senha: 'senha10' },
            { id: 11, nome: 'Usuario 11', senha: 'senha11' },
            { id: 12, nome: 'Usuario 12', senha: 'senha12' },
            { id: 13, nome: 'Usuario 13', senha: 'senha13' },
            { id: 14, nome: 'Usuario 14', senha: 'senha14' },
            { id: 15, nome: 'Usuario 15', senha: 'senha15' },
            { id: 16, nome: 'Usuario 16', senha: 'senha16' },
            { id: 17, nome: 'Usuario 17', senha: 'senha17' },
            { id: 18, nome: 'Usuario 18', senha: 'senha18' },
            { id: 19, nome: 'Usuario 19', senha: 'senha19' },
            { id: 20, nome: 'Usuario 20', senha: 'senha20' },
        ]);
        const currentPage = ref(1);
        const perPage = ref(16);

        const paginatedUsuarios = computed(() => {
            const start = (currentPage.value - 1) * perPage.value;
            const end = start + perPage.value;
            return usuarios.value.slice(start, end);
        });

        const updatePage = (page) => {
            if (page >= 1 && page <= Math.ceil(usuarios.value.length / perPage.value))
            currentPage.value = page;
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

tr{
    background-color: #EDEDED;
}

tr:nth-child(even) {
    background-color: #E3E3E3;
}

#selectionPage-container{
    display: flex;
    margin-top: 10px;
}

#page-label{
    margin: 0 10px;
}

#nome-coluna{
    width: 65%;
}
</style>