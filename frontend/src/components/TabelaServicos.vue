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
                <tr v-for="servico in servicos" :key="servico.Id">
                    <td>{{ servico.id }}</td>
                    <td>{{ servico.nome }}</td>
                    <td>{{ servico.unidade }}</td>
                    <td>{{ servico.preço }}</td>
                </tr>
            </tbody>
        </table>
        <div  id="selectionPage-container">
            <h1 @click="updatePage(currentPage - 1)">&lt;</h1>
            <h1 id="page-label">{{currentPage}}</h1>
            <div @click="updatePage(currentPage + 1)"><h1>&gt;</h1> </div>
        </div>
    </div>
</template>

<script>
import { ref, computed, onMounted, onBeforeUnmount } from 'vue';
import api from "../services/axios";

export default {
    name: 'TabelaServicos',
    setup() {
        const servicos = ref([]);
        const currentPage = ref(1);
        const perPage = ref(16);

        const fetchServicos = async (page) => {
            try {
                const response = await api.get('/item', {
                    params: {
                        type: 1,
                        page: page - 1,
                        size: perPage.value
                    }
                });
                
                servicos.value = response.data.content.map(servico => ({
                    id: servico.id,
                    nome: servico.name,
                    unidade:servico.unidade,
                    preço :servico.valor
                }));

                currentPage.value = page;

            } catch (error) {
                console.error("Erro ao buscar servicos:", error);
            }
        };

        

        const handleUserRegistered = () => {
            fetchServicos(currentPage.value);
        };

        onMounted(() => {
            fetchServicos(currentPage.value);
            window.addEventListener('servico-registered', handleUserRegistered);
        });

        onBeforeUnmount(() => {
            window.removeEventListener('servico-registered', handleUserRegistered);
        });

        const updatePage = (page) => { {
            if (page > 0){
                fetchServicos(page);
            }
                
        }};

        return {
            servicos,
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