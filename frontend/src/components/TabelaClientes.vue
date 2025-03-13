<template>
    <div>
        <table>
            <thead>
                <tr>
                    <th id="id-coluna">ID</th>
                    <th id="nome-coluna">Nome</th>
                    <th >CNPJ</th>
                </tr>
            </thead>
            <tbody>
                <tr v-for="cliente in clientes" :key="cliente.id">
                    <td>{{ cliente.id }}</td>
                    <td >{{ cliente.nome }}</td>
                    <td>{{ cliente.cnpj }}</td>
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
import { ref, onMounted, onBeforeUnmount } from 'vue';
import api from "../services/axios";

export default {
    name: 'TabelaClientes',
    setup() {
        const clientes = ref([]);
        const currentPage = ref(1);
        const perPage = ref(16);

        const fetchClientes = async (page) => {
            try {
                const response = await api.get('/cliente', {
                    params: {
                        page: page - 1,
                        size: perPage.value
                    }
                });
                
                clientes.value = response.data.content.map(client => ({
                    id: client.id,
                    nome: client.name,
                    cnpj: client.cnpj,
                }));
                
                console.log(response.data.content);
                console.log(clientes.value);

                currentPage.value = page;

            } catch (error) {
                console.error("Erro ao buscar clientes:", error);
            }
        };

        

        const handleUserRegistered = () => {
            fetchClientes(currentPage.value);
        };

        onMounted(() => {
            fetchClientes(currentPage.value);
            window.addEventListener('client-registered', handleUserRegistered);
        });

        onBeforeUnmount(() => {
            window.removeEventListener('client-registered', handleUserRegistered);
        });

        const updatePage = (page) => { {
            if (page > 0){
                fetchClientes(page);
            }
                
        }};

        return {
            clientes,
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

#id-coluna{
    width: 7%;
}
</style>