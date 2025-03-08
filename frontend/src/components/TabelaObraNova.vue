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
                    <td>{{ etapa.id }}</td>
                    <td>{{ etapa.etapa }}</td>
                    <td>R${{ etapa.valor }}</td>
                    <td>{{ etapa.prazo }}</td>
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
    props: ['values'],
    setup(props) {
        const etapas = ref([]);
        const currentPage = ref(1);
        const perPage = ref(15);

        etapas.value = props.values;

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