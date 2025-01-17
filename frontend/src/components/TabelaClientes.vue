<template>
    <div>
        <table>
            <thead>
                <tr>
                    <th id="nome-coluna">Nome</th>
                    <th >CNPJ</th>
                </tr>
            </thead>
            <tbody>
                <tr v-for="cliente in paginatedClientes" :key="cliente.id">
                    <td >{{ cliente.nome }}</td>
                    <td>{{ cliente.cnpj }}</td>
                </tr>
            </tbody>
        </table>
        <pagination
            :data="clientes"
            :limit="5"
            @pagination-change-page="updatePage"
        ></pagination>
        <div v-if="clientes.length > perPage" id="selectionPage-container">
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
    name: 'TabelaClientes',
    components: {
        Pagination
    },
    setup() {
        const clientes = ref([
            { id: 1, nome: 'Cliente 1', cnpj: '00.000.000/0000-00' },
            { id: 2, nome: 'Cliente 2', cnpj: '11.111.111/1111-11' },
            { id: 3, nome: 'Cliente 3', cnpj: '22.222.222/2222-22' },
            { id: 4, nome: 'Cliente 4', cnpj: '33.333.333/3333-33' },
            { id: 5, nome: 'Cliente 5', cnpj: '44.444.444/4444-44' },
            { id: 6, nome: 'Cliente 6', cnpj: '55.555.555/5555-55' },
            { id: 7, nome: 'Cliente 7', cnpj: '66.666.666/6666-66' },
            { id: 8, nome: 'Cliente 8', cnpj: '77.777.777/7777-77' },
            { id: 9, nome: 'Cliente 9', cnpj: '88.888.888/8888-88' },
            { id: 10, nome: 'Cliente 10', cnpj: '99.999.999/9999-99' },
            { id: 10, nome: 'Cliente 10', cnpj: '99.999.999/9999-99' },
            { id: 10, nome: 'Cliente 10', cnpj: '99.999.999/9999-99' },
            { id: 10, nome: 'Cliente 10', cnpj: '99.999.999/9999-99' },
            { id: 10, nome: 'Cliente 10', cnpj: '99.999.999/9999-99' },
            { id: 10, nome: 'Cliente 10', cnpj: '99.999.999/9999-99' },
            { id: 10, nome: 'Cliente 10', cnpj: '99.999.999/9999-99' },
            { id: 10, nome: 'Cliente 10', cnpj: '99.999.999/9999-99' },
            { id: 10, nome: 'Cliente 10', cnpj: '99.999.999/9999-99' },
            { id: 10, nome: 'Cliente 10', cnpj: '99.999.999/9999-99' },
            { id: 10, nome: 'Cliente 10', cnpj: '99.999.999/9999-99' },
            { id: 10, nome: 'Cliente 10', cnpj: '99.999.999/9999-99' },
            { id: 10, nome: 'Cliente 10', cnpj: '99.999.999/9999-99' },
            { id: 10, nome: 'Cliente 10', cnpj: '99.999.999/9999-99' },
            { id: 10, nome: 'Cliente 10', cnpj: '99.999.999/9999-99' },
            { id: 10, nome: 'Cliente 10', cnpj: '99.999.999/9999-99' },
            // Adicione mais clientes conforme necessário
        ]);
        const currentPage = ref(1);
        const perPage = ref(15);

        const paginatedClientes = computed(() => {
            const start = (currentPage.value - 1) * perPage.value;
            const end = start + perPage.value;
            return clientes.value.slice(start, end);
        });

        const updatePage = (page) => {
            if (page >= 1 && page <= Math.ceil(clientes.value.length / perPage.value)){
                currentPage.value = page;
            }
        };

        return {
            clientes,
            currentPage,
            perPage,
            paginatedClientes,
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