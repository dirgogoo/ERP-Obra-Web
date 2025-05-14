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
                    <th id="coluna-fornecedor">Fornecedor</th>
                </tr>
            </thead>
            <tbody >
                <tr v-for="item in items" :key="item.Id" @click="handleItemSelected(item)">
                    <td>{{ item.id }}</td>
                    <td>{{ item.name }}</td>
                    <td>{{ item.tipo }}</td>
                    <td>{{ item.unidade }}</td>
                    <td>R${{ item.valor }}</td>
                    <td>{{ item.fornecedor.nome }}</td>
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
import { ref, onMounted, watch } from 'vue';
import api from "../services/axios";

const props = defineProps({
    modelValue: {
        type: Array,
        required: true
    },
    search: {
        type: String,
    }
});

watch(() => props.search, () => {
    fetchItens(currentPage.value);
});

const emit = defineEmits(['update:modelValue']);

const itemSelected = ref(props.modelValue);


const items = ref([]);
const currentPage = ref(1);
const perPage = ref(8);

const fetchItens = async (page) => {
    try {
        const response = await api.get('/item', {
            params: {
                page: page - 1,
                size: perPage.value,
                search: props.search,
                sort: 'id,desc',
            }
        });
        console.log(response.data.content);
        items.value = response.data.content
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

#coluna-fornecedor{
    width: 20%;
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