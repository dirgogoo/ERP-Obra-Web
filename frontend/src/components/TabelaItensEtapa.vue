<template>
    <div>
        <table>
            <thead>
                <tr>
                    <th id="coluna-id">ID</th>
                    <th>Nome</th>
                    <th id="coluna-tipo">Tipo</th>
                    <th id="coluna-unidade">Und</th>
                    <th id="coluna-qtd">QTD</th>
                    <th id="coluna-preco">Preço Total</th>
                </tr>
            </thead>
            <tbody>
                <tr v-for="item in paginatedItens" :key="item.Id">
                    <td>{{ item.Id }}</td>
                    <td>{{ item.Nome }}</td>
                    <td>{{ item.Tipo }}</td>
                    <td>{{ item.Und }}</td>
                    <td>{{ item.Qtd }}</td>
                    <td>R${{ item.Preço * item.Qtd }}</td>
                </tr>
            </tbody>
        </table>
        <pagination
            :data="itens"
            :limit="15"
            @pagination-change-page="updatePage"
        ></pagination>
        <div v-if="itens.length > perPage" id="selectionPage-container">
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
    name: 'TabelaItens',
    components: {
        Pagination
    },
    setup() {
        const itens = ref([
            { Id: 1, Nome: 'Item 1', Tipo: 'Tipo A', Und: 'kg', Preço: 10.0, Qtd: 1 },
            { Id: 2, Nome: 'Item 2', Tipo: 'Tipo B', Und: 'm', Preço: 20.0, Qtd: 2 },
            { Id: 3, Nome: 'Item 3', Tipo: 'Tipo C', Und: 'l', Preço: 30.0, Qtd: 3 },
            { Id: 4, Nome: 'Item 4', Tipo: 'Tipo A', Und: 'kg', Preço: 40.0, Qtd: 4 },
            { Id: 5, Nome: 'Item 5', Tipo: 'Tipo B', Und: 'm', Preço: 50.0, Qtd: 5 },
            { Id: 6, Nome: 'Item 6', Tipo: 'Tipo C', Und: 'l', Preço: 60.0, Qtd: 6 },
            { Id: 7, Nome: 'Item 7', Tipo: 'Tipo A', Und: 'kg', Preço: 70.0, Qtd: 7 },
            { Id: 8, Nome: 'Item 8', Tipo: 'Tipo B', Und: 'm', Preço: 80.0, Qtd: 8 },
            { Id: 9, Nome: 'Item 9', Tipo: 'Tipo C', Und: 'l', Preço: 90.0, Qtd: 9 },
            { Id: 10, Nome: 'Item 10', Tipo: 'Tipo A', Und: 'kg', Preço: 100.0, Qtd: 10 },
            { Id: 11, Nome: 'Item 11', Tipo: 'Tipo B', Und: 'm', Preço: 110.0, Qtd: 11 },
            { Id: 12, Nome: 'Item 12', Tipo: 'Tipo C', Und: 'l', Preço: 120.0, Qtd: 12 },
            { Id: 13, Nome: 'Item 13', Tipo: 'Tipo A', Und: 'kg', Preço: 130.0, Qtd: 13 },
            { Id: 14, Nome: 'Item 14', Tipo: 'Tipo B', Und: 'm', Preço: 140.0, Qtd: 14 },
            { Id: 15, Nome: 'Item 15', Tipo: 'Tipo C', Und: 'l', Preço: 150.0, Qtd: 15 },
            { Id: 16, Nome: 'Item 16', Tipo: 'Tipo A', Und: 'kg', Preço: 160.0, Qtd: 16 },
            { Id: 17, Nome: 'Item 17', Tipo: 'Tipo B', Und: 'm', Preço: 170.0, Qtd: 17 },
            { Id: 18, Nome: 'Item 18', Tipo: 'Tipo C', Und: 'l', Preço: 180.0, Qtd: 18 },
            { Id: 19, Nome: 'Item 19', Tipo: 'Tipo A', Und: 'kg', Preço: 190.0, Qtd: 19 },
            { Id: 20, Nome: 'Item 20', Tipo: 'Tipo B', Und: 'm', Preço: 200.0, Qtd: 20 },
            { Id: 21, Nome: 'Item 21', Tipo: 'Tipo C', Und: 'l', Preço: 210.0, Qtd: 21 },
            { Id: 22, Nome: 'Item 22', Tipo: 'Tipo A', Und: 'kg', Preço: 220.0, Qtd: 22 },
            { Id: 23, Nome: 'Item 23', Tipo: 'Tipo B', Und: 'm', Preço: 230.0, Qtd: 23 },
            { Id: 24, Nome: 'Item 24', Tipo: 'Tipo C', Und: 'l', Preço: 240.0, Qtd: 24 },
            { Id: 25, Nome: 'Item 25', Tipo: 'Tipo A', Und: 'kg', Preço: 250.0, Qtd: 25 },
            { Id: 26, Nome: 'Item 26', Tipo: 'Tipo B', Und: 'm', Preço: 260.0, Qtd: 26 },
            { Id: 27, Nome: 'Item 27', Tipo: 'Tipo C', Und: 'l', Preço: 270.0, Qtd: 27 },
            { Id: 28, Nome: 'Item 28', Tipo: 'Tipo A', Und: 'kg', Preço: 280.0, Qtd: 28 },
            { Id: 29, Nome: 'Item 29', Tipo: 'Tipo B', Und: 'm', Preço: 290.0, Qtd: 29 },
            { Id: 30, Nome: 'Item 30', Tipo: 'Tipo C', Und: 'l', Preço: 300.0, Qtd: 30 },
            { Id: 31, Nome: 'Item 31', Tipo: 'Tipo A', Und: 'kg', Preço: 310.0, Qtd: 31 },
            { Id: 32, Nome: 'Item 32', Tipo: 'Tipo B', Und: 'm', Preço: 320.0, Qtd: 32 },
            { Id: 33, Nome: 'Item 33', Tipo: 'Tipo C', Und: 'l', Preço: 330.0, Qtd: 33 },
            { Id: 34, Nome: 'Item 34', Tipo: 'Tipo A', Und: 'kg', Preço: 340.0, Qtd: 34 },
            { Id: 35, Nome: 'Item 35', Tipo: 'Tipo B', Und: 'm', Preço: 350.0, Qtd: 35 },
            { Id: 36, Nome: 'Item 36', Tipo: 'Tipo C', Und: 'l', Preço: 360.0, Qtd: 36 },
            { Id: 37, Nome: 'Item 37', Tipo: 'Tipo A', Und: 'kg', Preço: 370.0, Qtd: 37 },
            { Id: 38, Nome: 'Item 38', Tipo: 'Tipo B', Und: 'm', Preço: 380.0, Qtd: 38 },
            { Id: 39, Nome: 'Item 39', Tipo: 'Tipo C', Und: 'l', Preço: 390.0, Qtd: 39 },
            { Id: 40, Nome: 'Item 40', Tipo: 'Tipo A', Und: 'kg', Preço: 400.0, Qtd: 40 },
        ]);
        const currentPage = ref(1);
        const perPage = ref(15);

        const paginatedItens = computed(() => {
            const start = (currentPage.value - 1) * perPage.value;
            const end = start + perPage.value;
            return itens.value.slice(start, end);
        });

        const updatePage = (page) => {
            if (page >= 1 && page <= Math.ceil(itens.value.length / perPage.value))
            currentPage.value = page;
        };

        return {
            itens,
            currentPage,
            perPage,
            paginatedItens,
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
    cursor: pointer;
}

#selectionPage-container{
    display: flex;
    margin-top: 10px;
}

#page-label{
    margin: 0 10px;
}

#coluna-tipo{
    width: 15%;
}

#coluna-id{
    width: 7%;
}

#coluna-unidade{
    width: 8%;
}

#coluna-qtd{
    width: 8%;
}

#coluna-preco{
    width: 15%;
}
</style>