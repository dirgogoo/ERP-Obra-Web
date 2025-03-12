<template>
    <div>
        <table>
            <thead>
                <tr>
                    <th id="coluna-id">ID</th>
                    <th>Nome</th>
                    <th id="coluna-tipo">Tipo</th>
                    <th id="coluna-unidade">Und</th>
                    <th id="coluna-preco">Preço</th>
                </tr>
            </thead>
            <tbody >
                <tr v-for="item in items" :key="item.Id" @click="handleItemSelected(item.nome)">
                    <td>{{ item.id }}</td>
                    <td>{{ item.nome }}</td>
                    <td>{{ item.tipo }}</td>
                    <td>{{ item.und }}</td>
                    <td>R${{ item.preço }}</td>
                </tr>
            </tbody>
        </table>
        <div id="selectionPage-container">
            <h1 @click="updatePage(currentPage - 1)">&lt;</h1>
            <h1 id="page-label">{{ currentPage }}</h1>
            <div @click="updatePage(currentPage + 1)">
                <h1>&gt;</h1>
            </div>
        </div>
    </div>
</template>

<script setup>
import { ref, onMounted } from 'vue';
import api from "../services/axios";

const props = defineProps({
    modelValue: {
        type: String,
        required: true
    }
});

const emit = defineEmits(['update:modelValue']);

const itemSelected = ref(props.modelValue);


const items = ref([]);
const currentPage = ref(1);
const perPage = ref(10);

const fetchItens = async (page) => {
    try {
        const response = await api.get('/item', {
            params: {
                page: page - 1,
                size: perPage.value
            }
        });
        console.log(response.data.content);
        items.value = response.data.content.map(item => ({
            id: item.id,
            nome: item.name,
            tipo: item.tipo,
            und: item.unidade,
            preço: item.valor,
        }));

        currentPage.value = page;

    } catch (error) {
        console.error("Erro ao buscar items:", error);
    }
};

const updatePage = (page) => {
    {
        if (page > 0) {
            fetchItens(page);
        }
    }
};

const handleItemSelected = (item) => {
    itemSelected.value = item;
    emit('update:modelValue', item);
};

onMounted(() => {
    fetchItens(currentPage.value);
});

</script>


<style scoped>
table {
    width: 100%;
    border-collapse: collapse;
}

th,
td {
    border: 1px solid #ddd;
    padding: 8px;
}

th {
    background-color: #2888E4;
    color: white;
}

tr {
    background-color: #EDEDED;
}

tr:nth-child(even) {
    background-color: #E3E3E3;
}

tr:hover {
    background-color: #b8d9ff;
    cursor: pointer;
}

#selectionPage-container {
    display: flex;
    margin-top: 10px;
}

#page-label {
    margin: 0 10px;
}

#coluna-tipo {
    width: 15%;
}

#coluna-id {
    width: 7%;
}

#coluna-unidade {
    width: 8%;
}

#coluna-preco {
    width: 15%;
}
</style>