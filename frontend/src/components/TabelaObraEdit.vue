<template>
    <div>
        <table>
            <thead>
                <tr>
                    <th id="id-collumn">Id</th>
                    <th>Etapa</th>
                    <th id="valor-collumn">Valor</th>
                    <th id="prazo-collumn">Prazo</th>
                    <th id="prazo-collumn">Inicio</th>
                </tr>
            </thead>
            <tbody>
                <tr v-for="etapa in paginatedEtapas" :key="etapa.Id">
                    <td>{{ etapa.id }}</td>
                    <td>{{ etapa.etapa.name }}</td>
                    <td>R${{ etapa.price }}</td>
                    <td>{{ etapa.deadline }}</td>
                    <td>{{ etapa.dataInicio}}</td>
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

<script setup>
import { ref, computed, watch, onMounted } from 'vue';
import Pagination from 'laravel-vue-pagination';


const props = defineProps({
    values: {
        type: Array,
        required: true
    },
});

        const etapas = ref([]);
        const currentPage = ref(1);
        const perPage = ref(15);

        watch(() => props.values, (newValues) => {
            etapas.value = newValues;
            console.log(newValues, "ETAPAS CHASNGED")
        }, { immediate: true });

        const paginatedEtapas = computed(() => {
            const start = (currentPage.value - 1) * perPage.value;
            const end = start + perPage.value;
            return etapas.value.slice(start, end);
        });

        console.log(etapas.value,"ETAPAS")

        const updatePage = (page) => {
            if (page >= 1 && page <= Math.ceil(etapas.value.length / perPage.value))
            currentPage.value = page;
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