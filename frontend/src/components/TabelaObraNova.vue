<template>
    <div>
        <table>
            <thead>
                <tr>
                    <th id="id-collumn">Id</th>
                    <th>Etapa</th>
                    <th id="valor-collumn">Valor</th>
                    <th id="prazo-collumn">Prazo</th>
                </tr>
            </thead>
            <tbody>
                <tr v-for="etapa in paginatedEtapas" :key="etapa.Id">
                    <td>{{ etapa.Id }}</td>
                    <td>{{ etapa.Etapa }}</td>
                    <td>R${{ etapa.Valor }}</td>
                    <td>{{ etapa.Prazo }}</td>
                </tr>
            </tbody>
        </table>
        <pagination
            :data="etapas"
            :limit="15"
            @pagination-change-page="updatePage"
        ></pagination>
        <div v-if="etapas.length > perPage" id="selectionPage-container">
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
    name: 'TabelaEtapaNova',
    components: {
        Pagination
    },
    setup() {
        const etapas = ref([
            { Id: 1, Etapa: 'Etapa 1', Valor: 1000, Prazo: '2023-01-01' },
            { Id: 2, Etapa: 'Etapa 2', Valor: 2000, Prazo: '2023-01-02' },
            { Id: 3, Etapa: 'Etapa 3', Valor: 3000, Prazo: '2023-01-03' },
            { Id: 4, Etapa: 'Etapa 4', Valor: 4000, Prazo: '2023-01-04' },
            { Id: 5, Etapa: 'Etapa 5', Valor: 5000, Prazo: '2023-01-05' },
            { Id: 6, Etapa: 'Etapa 6', Valor: 6000, Prazo: '2023-01-06' },
            { Id: 7, Etapa: 'Etapa 7', Valor: 7000, Prazo: '2023-01-07' },
            { Id: 8, Etapa: 'Etapa 8', Valor: 8000, Prazo: '2023-01-08' },
            { Id: 9, Etapa: 'Etapa 9', Valor: 9000, Prazo: '2023-01-09' },
            { Id: 10, Etapa: 'Etapa 10', Valor: 10000, Prazo: '2023-01-10' },
            { Id: 11, Etapa: 'Etapa 11', Valor: 11000, Prazo: '2023-01-11' },
            { Id: 12, Etapa: 'Etapa 12', Valor: 12000, Prazo: '2023-01-12' },
            { Id: 13, Etapa: 'Etapa 13', Valor: 13000, Prazo: '2023-01-13' },
            { Id: 14, Etapa: 'Etapa 14', Valor: 14000, Prazo: '2023-01-14' },
            { Id: 15, Etapa: 'Etapa 15', Valor: 15000, Prazo: '2023-01-15' },
            { Id: 16, Etapa: 'Etapa 16', Valor: 16000, Prazo: '2023-01-16' },
            { Id: 17, Etapa: 'Etapa 17', Valor: 17000, Prazo: '2023-01-17' },
            { Id: 18, Etapa: 'Etapa 18', Valor: 18000, Prazo: '2023-01-18' },
            { Id: 19, Etapa: 'Etapa 19', Valor: 19000, Prazo: '2023-01-19' },
            { Id: 20, Etapa: 'Etapa 20', Valor: 20000, Prazo: '2023-01-20' },
            { Id: 21, Etapa: 'Etapa 21', Valor: 21000, Prazo: '2023-01-21' },
            { Id: 22, Etapa: 'Etapa 22', Valor: 22000, Prazo: '2023-01-22' },
            { Id: 23, Etapa: 'Etapa 23', Valor: 23000, Prazo: '2023-01-23' },
            { Id: 24, Etapa: 'Etapa 24', Valor: 24000, Prazo: '2023-01-24' },
            { Id: 25, Etapa: 'Etapa 25', Valor: 25000, Prazo: '2023-01-25' },
            { Id: 26, Etapa: 'Etapa 26', Valor: 26000, Prazo: '2023-01-26' },
            { Id: 27, Etapa: 'Etapa 27', Valor: 27000, Prazo: '2023-01-27' },
            { Id: 28, Etapa: 'Etapa 28', Valor: 28000, Prazo: '2023-01-28' },
            { Id: 29, Etapa: 'Etapa 29', Valor: 29000, Prazo: '2023-01-29' },
            { Id: 30, Etapa: 'Etapa 30', Valor: 30000, Prazo: '2023-01-30' },
            { Id: 31, Etapa: 'Etapa 31', Valor: 31000, Prazo: '2023-01-31' },
            { Id: 32, Etapa: 'Etapa 32', Valor: 32000, Prazo: '2023-02-01' },
            { Id: 33, Etapa: 'Etapa 33', Valor: 33000, Prazo: '2023-02-02' },
            { Id: 34, Etapa: 'Etapa 34', Valor: 34000, Prazo: '2023-02-03' },
            { Id: 35, Etapa: 'Etapa 35', Valor: 35000, Prazo: '2023-02-04' },
            { Id: 36, Etapa: 'Etapa 36', Valor: 36000, Prazo: '2023-02-05' },
            { Id: 37, Etapa: 'Etapa 37', Valor: 37000, Prazo: '2023-02-06' },
            { Id: 38, Etapa: 'Etapa 38', Valor: 38000, Prazo: '2023-02-07' },
            { Id: 39, Etapa: 'Etapa 39', Valor: 39000, Prazo: '2023-02-08' },
            { Id: 40, Etapa: 'Etapa 40', Valor: 40000, Prazo: '2023-02-09' },
        ]);
        const currentPage = ref(1);
        const perPage = ref(15);

        const paginatedEtapas = computed(() => {
            const start = (currentPage.value - 1) * perPage.value;
            const end = start + perPage.value;
            return etapas.value.slice(start, end);
        });

        const updatePage = (page) => {
            if (page >= 1 && page <= Math.ceil(etapas.value.length / perPage.value))
            currentPage.value = page;
        };

        return {
            etapas,
            currentPage,
            perPage,
            paginatedEtapas,
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

tr:hover{
    background-color: #b8d9ff;
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

#valor-collumn{
    width: 20%;
}

#prazo-collumn{
    width: 15%;
}

</style>3