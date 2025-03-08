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
                <tr v-for="obra in obras" :key="obra.Id" @click="toRouteId(obra.id)">
                    <td>{{ obra.id }}</td>
                    <td>{{ obra.nome }}</td>
                    <td>{{ obra.cliente }}</td>
                    <td>{{ obra.dataCriacao }}</td>
                    <td>{{ obra.status }}</td>
                </tr>
            </tbody>
        </table>
        <div v-if="obras.length > perPage" id="selectionPage-container">
            <h1 @click="updatePage(currentPage - 1)">&lt;</h1>
            <h1 id="page-label">{{currentPage}}</h1>
            <div @click="updatePage(currentPage + 1)"><h1>&gt;</h1> </div>
        </div>
    </div>
</template>

<script>
import { ref, computed, onMounted, onBeforeUnmount } from 'vue';
import { useRouter } from 'vue-router';
import api from "../services/axios";

export default {
    name: 'TabelaObras',
    setup() {
        const obras = ref([]);
        const currentPage = ref(1);
        const perPage = ref(16);

        const fetchObras = async (page) => {
            try {
                const response = await api.get('/obra', {
                    params: {
                        type: 2,
                        page: page - 1,
                        size: perPage.value
                    }
                });
                
                obras.value = response.data.content.map(obra => ({
                    id: obra.id,
                    nome: obra.nome,
                    cliente: obra.cliente,
                    dataCriacao : obra.dataLancamento,
                    status: obra.status
                }));

                currentPage.value = page;

            } catch (error) {
                console.error("Erro ao buscar obras:", error);
            }
        };

        

        const handleUserRegistered = () => {
            fetchObras(currentPage.value);
        };

        onMounted(() => {
            fetchObras(currentPage.value);
            window.addEventListener('obra-registered', handleUserRegistered);
        });

        onBeforeUnmount(() => {
            window.removeEventListener('obra-registered', handleUserRegistered);
        });

        const router = useRouter();

        const toRouteId = (id) => {
            router.push(`/app/obra/${id}`);
        };

        const updatePage = (page) => { {
            if (page > 0){
                fetchObras(page);
            }
                
        }};

        return {
            obras,
            currentPage,
            perPage,
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
</style>