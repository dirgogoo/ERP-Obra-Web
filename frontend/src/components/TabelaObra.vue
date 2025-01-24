<template>
    <div>
        <table>
            <thead>
                <tr>
                    <th id="id-collumn">Id</th>
                    <th>Nome</th>
                    <th id="client-collumn">Cliente</th>
                    <th id="date-collumn">Data de Criação</th>
                    <th id="status-collumn">Status</th>
                </tr>
            </thead>
            <tbody>
                <tr v-for="obra in paginatedObras" :key="obra.Id" @click="toRouteId(obra.Id)">
                    <td>{{ obra.Id }}</td>
                    <td>{{ obra.Nome }}</td>
                    <td>{{ obra.Cliente }}</td>
                    <td>{{ obra.DataCriacao }}</td>
                    <td>{{ obra.Status }}</td>
                </tr>
            </tbody>
        </table>
        <pagination
            :data="obras"
            :limit="15"
            @pagination-change-page="updatePage"
        ></pagination>
        <div v-if="obras.length > perPage" id="selectionPage-container">
            <h1 @click="updatePage(currentPage - 1)">&lt;</h1>
            <h1 id="page-label">{{currentPage}}</h1>
            <div @click="updatePage(currentPage + 1)"><h1>&gt;</h1> </div>
        </div>
    </div>
</template>

<script>
import { ref, computed } from 'vue';
import Pagination from 'laravel-vue-pagination';
import { useRouter } from 'vue-router';
export default {

    name: 'TabelaObra',
    components: {
        Pagination
    },

    setup() {
        const obras = ref([
            { Id: 1, Nome: 'Obra 1', Cliente: 'Cliente 1', DataCriacao: '2023-01-01', Status: 'Em andamento' },
            { Id: 2, Nome: 'Obra 2', Cliente: 'Cliente 2', DataCriacao: '2023-01-02', Status: 'Concluída' },
            { Id: 3, Nome: 'Obra 3', Cliente: 'Cliente 3', DataCriacao: '2023-01-03', Status: 'Pendente' },
            { Id: 4, Nome: 'Obra 4', Cliente: 'Cliente 4', DataCriacao: '2023-01-04', Status: 'Em andamento' },
            { Id: 5, Nome: 'Obra 5', Cliente: 'Cliente 5', DataCriacao: '2023-01-05', Status: 'Concluída' },
            { Id: 6, Nome: 'Obra 6', Cliente: 'Cliente 6', DataCriacao: '2023-01-06', Status: 'Pendente' },
            { Id: 7, Nome: 'Obra 7', Cliente: 'Cliente 7', DataCriacao: '2023-01-07', Status: 'Em andamento' },
            { Id: 8, Nome: 'Obra 8', Cliente: 'Cliente 8', DataCriacao: '2023-01-08', Status: 'Concluída' },
            { Id: 9, Nome: 'Obra 9', Cliente: 'Cliente 9', DataCriacao: '2023-01-09', Status: 'Pendente' },
            { Id: 10, Nome: 'Obra 10', Cliente: 'Cliente 10', DataCriacao: '2023-01-10', Status: 'Em andamento' },
            { Id: 11, Nome: 'Obra 11', Cliente: 'Cliente 11', DataCriacao: '2023-01-11', Status: 'Em andamento' },
            { Id: 12, Nome: 'Obra 12', Cliente: 'Cliente 12', DataCriacao: '2023-01-12', Status: 'Concluída' },
            { Id: 13, Nome: 'Obra 13', Cliente: 'Cliente 13', DataCriacao: '2023-01-13', Status: 'Pendente' },
            { Id: 14, Nome: 'Obra 14', Cliente: 'Cliente 14', DataCriacao: '2023-01-14', Status: 'Em andamento' },
            { Id: 15, Nome: 'Obra 15', Cliente: 'Cliente 15', DataCriacao: '2023-01-15', Status: 'Concluída' },
            { Id: 16, Nome: 'Obra 16', Cliente: 'Cliente 16', DataCriacao: '2023-01-16', Status: 'Pendente' },
            { Id: 17, Nome: 'Obra 17', Cliente: 'Cliente 17', DataCriacao: '2023-01-17', Status: 'Em andamento' },
            { Id: 18, Nome: 'Obra 18', Cliente: 'Cliente 18', DataCriacao: '2023-01-18', Status: 'Concluída' },
            { Id: 19, Nome: 'Obra 19', Cliente: 'Cliente 19', DataCriacao: '2023-01-19', Status: 'Pendente' },
            { Id: 20, Nome: 'Obra 20', Cliente: 'Cliente 20', DataCriacao: '2023-01-20', Status: 'Em andamento' },
            { Id: 21, Nome: 'Obra 21', Cliente: 'Cliente 21', DataCriacao: '2023-01-21', Status: 'Em andamento' },
            { Id: 22, Nome: 'Obra 22', Cliente: 'Cliente 22', DataCriacao: '2023-01-22', Status: 'Concluída' },
            { Id: 23, Nome: 'Obra 23', Cliente: 'Cliente 23', DataCriacao: '2023-01-23', Status: 'Pendente' },
            { Id: 24, Nome: 'Obra 24', Cliente: 'Cliente 24', DataCriacao: '2023-01-24', Status: 'Em andamento' },
            { Id: 25, Nome: 'Obra 25', Cliente: 'Cliente 25', DataCriacao: '2023-01-25', Status: 'Concluída' },
            { Id: 26, Nome: 'Obra 26', Cliente: 'Cliente 26', DataCriacao: '2023-01-26', Status: 'Pendente' },
            { Id: 27, Nome: 'Obra 27', Cliente: 'Cliente 27', DataCriacao: '2023-01-27', Status: 'Em andamento' },
            { Id: 28, Nome: 'Obra 28', Cliente: 'Cliente 28', DataCriacao: '2023-01-28', Status: 'Concluída' },
            { Id: 29, Nome: 'Obra 29', Cliente: 'Cliente 29', DataCriacao: '2023-01-29', Status: 'Pendente' },
            { Id: 30, Nome: 'Obra 30', Cliente: 'Cliente 30', DataCriacao: '2023-01-30', Status: 'Em andamento' },
            { Id: 31, Nome: 'Obra 31', Cliente: 'Cliente 31', DataCriacao: '2023-01-31', Status: 'Em andamento' },
            { Id: 32, Nome: 'Obra 32', Cliente: 'Cliente 32', DataCriacao: '2023-02-01', Status: 'Concluída' },
            { Id: 33, Nome: 'Obra 33', Cliente: 'Cliente 33', DataCriacao: '2023-02-02', Status: 'Pendente' },
            { Id: 34, Nome: 'Obra 34', Cliente: 'Cliente 34', DataCriacao: '2023-02-03', Status: 'Em andamento' },
            { Id: 35, Nome: 'Obra 35', Cliente: 'Cliente 35', DataCriacao: '2023-02-04', Status: 'Concluída' },
            { Id: 36, Nome: 'Obra 36', Cliente: 'Cliente 36', DataCriacao: '2023-02-05', Status: 'Pendente' },
            { Id: 37, Nome: 'Obra 37', Cliente: 'Cliente 37', DataCriacao: '2023-02-06', Status: 'Em andamento' },
            { Id: 38, Nome: 'Obra 38', Cliente: 'Cliente 38', DataCriacao: '2023-02-07', Status: 'Concluída' },
            { Id: 39, Nome: 'Obra 39', Cliente: 'Cliente 39', DataCriacao: '2023-02-08', Status: 'Pendente' },
            { Id: 40, Nome: 'Obra 40', Cliente: 'Cliente 40', DataCriacao: '2023-02-09', Status: 'Em andamento' },
        ]);

       
        const route = useRouter();

        const currentPage = ref(1);
        const perPage = ref(14);

        const paginatedObras = computed(() => {
            const start = (currentPage.value - 1) * perPage.value;
            const end = start + perPage.value;
            return obras.value.slice(start, end);
        });

        const updatePage = (page) => {
             if (page >= 1 && page <= Math.ceil(obras.value.length / perPage.value)){
                currentPage.value = page;
            }
        };

        const toRouteId = (id) => {
            route.push(`/app/obra/${id}`);
        };

        return {
            obras,
            currentPage,
            perPage,
            paginatedObras,
            updatePage,
            toRouteId
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

tr:hover{
    background-color: #b8d9ff;
    cursor: pointer;
}

#selectionPage-container{
    display: flex;
    margin-top: 10px;
}

#page-label{
    margin: 0 10px;
}

#id-collumn{
    width: 7%;
}

#client-collumn{
    width: 15%;
}

#date-collumn{
    width: 10%;
}

#status-collumn{
    width: 15%;
}
</style>3