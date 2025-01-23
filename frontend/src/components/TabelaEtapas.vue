<template>
    <div>
        <table>
            <thead>
                <tr>
                    <th id="numero-coluna">ID</th>
                    <th>Nome</th>
                </tr>
            </thead>
            <tbody>
                <tr v-for="etapa in paginatedEtapas" :key="etapa.id">
                    <td>{{ etapa.id }}</td>
                    <td>{{ etapa.nome }}</td>
                </tr>
            </tbody>
        </table>
        <pagination
            :data="etapas"
            :limit="5"
            @pagination-change-page="updatePage"
        ></pagination>
    </div>
</template>

<script>
import { ref, computed } from 'vue';
import Pagination from 'laravel-vue-pagination';

export default {
    name: 'TabelaEtapas',
    components: {
        Pagination
    },
    setup() {
        const etapas = ref([
            { id: 1, nome: 'Etapa 1' },
            { id: 2, nome: 'Etapa 2' },
            { id: 3, nome: 'Etapa 3' },
            { id: 4, nome: 'Etapa 4' },
            { id: 5, nome: 'Etapa 5' },
            { id: 6, nome: 'Etapa 6' },
            { id: 7, nome: 'Etapa 7' },
            { id: 8, nome: 'Etapa 8' },
            { id: 9, nome: 'Etapa 9' },
            { id: 10, nome: 'Etapa 10' },
            // Adicione mais etapas conforme necessário
        ]);
        const currentPage = ref(1);
        const perPage = ref(5);

        const paginatedEtapas = computed(() => {
            const start = (currentPage.value - 1) * perPage.value;
            const end = start + perPage.value;
            return etapas.value.slice(start, end);
        });

        const updatePage = (page) => {
            if (page >= 1 && page <= Math.ceil(etapas.value.length / perPage.value)){
                currentPage.value = page;
            }
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

#selectionPage-container{
    display: flex;
    margin-top: 10px;
}

#page-label{
    margin: 0 10px;
}

#numero-coluna{
    width: 7%;
}
</style>