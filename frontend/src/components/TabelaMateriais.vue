<template>
    <div>
        <table>
            <thead>
                <tr>
                    <th id="coluna-id">ID</th>
                    <th>Nome</th>
                    <th id="coluna-unidade">Unidade</th>
                    <th id="coluna-preco">Preço</th>
                </tr>
            </thead>
            <tbody>
                <tr v-for="material in paginatedMateriais" :key="material.Id">
                    <td>{{ material.Id }}</td>
                    <td>{{ material.Nome }}</td>
                    <td>{{ material.Unidade }}</td>
                    <td>R${{ material.Preço }}</td>
                </tr>
            </tbody>
        </table>
        <pagination
            :data="materiais"
            :limit="14"
            @pagination-change-page="updatePage"
        ></pagination>
        <div v-if="materiais.length > perPage" id="selectionPage-container">
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
    name: 'TabelaMaterial',
    components: {
        Pagination
    },
    setup() {
        const materiais = ref([
            { Id: 1, Nome: 'Material 1', Unidade: 'kg', Preço: 10.0 },
            { Id: 2, Nome: 'Material 2', Unidade: 'm', Preço: 20.0 },
            { Id: 3, Nome: 'Material 3', Unidade: 'l', Preço: 30.0 },
            { Id: 4, Nome: 'Material 4', Unidade: 'kg', Preço: 40.0 },
            { Id: 5, Nome: 'Material 5', Unidade: 'm', Preço: 50.0 },
            { Id: 6, Nome: 'Material 6', Unidade: 'l', Preço: 60.0 },
            { Id: 7, Nome: 'Material 7', Unidade: 'kg', Preço: 70.0 },
            { Id: 8, Nome: 'Material 8', Unidade: 'm', Preço: 80.0 },
            { Id: 9, Nome: 'Material 9', Unidade: 'l', Preço: 90.0 },
            { Id: 10, Nome: 'Material 10', Unidade: 'kg', Preço: 100.0 },
            // Adicione mais materiais conforme necessário
        ]);
        const currentPage = ref(1);
        const perPage = ref(14);

        const paginatedMateriais = computed(() => {
            const start = (currentPage.value - 1) * perPage.value;
            const end = start + perPage.value;
            return materiais.value.slice(start, end);
        });

        const updatePage = (page) => {
            currentPage.value = page;
        };

        return {
            materiais,
            currentPage,
            perPage,
            paginatedMateriais,
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

#coluna-id{
    width: 7%;
}

#coluna-unidade{
    width: 8%;
}
#coluna-preco{
    width: 15%;
}
</style>