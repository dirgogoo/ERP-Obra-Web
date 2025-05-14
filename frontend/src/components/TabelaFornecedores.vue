<template>
    <div>
        <table>
            <thead>
                <tr>
                    <th id="id-coluna">ID</th>
                    <th id="nome-coluna">Nome</th>
                    <th id="cnpj-coluna">CNPJ</th>
                    <th>Regiao</th>
                </tr>
            </thead>
            <tbody>
                <tr v-for="fornecedor in fornecedores" :key="fornecedor.id" :class="{selected: fornecedor.id === selectedId}" @click="selectRow(fornecedor.id)">
                    <td>{{ fornecedor.id }}</td>
                    <td>{{ fornecedor.nome }}</td>
                    <td>{{ fornecedor.cnpj }}</td>
                    <td>{{ fornecedor.regiao.nome }}</td>
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
import { ref, onMounted, onBeforeUnmount, watch } from 'vue';
import api from "../services/axios";


const fornecedores = ref([]);
const currentPage = ref(1);
const perPage = ref(16);

const props = defineProps({
    search: {
        type: String,
    },
    modelValue: {
        type: Number,
        required: true
    },
});

const emit = defineEmits(['update:modelValue']);
const selectedId = ref(null);

watch(() => props.search, () => {
    fetchFornecedores(currentPage.value);
});

const fetchFornecedores = async (page) => {
    try {
        const response = await api.get('/fornecedor', {
            params: {
                page: page - 1,
                size: perPage.value,
                search: props.search,
                sort: 'id,desc',
            }
        });

        fornecedores.value = response.data.content

        console.log(response.data.content);
        console.log(fornecedores.value);

        currentPage.value = page;

    } catch (error) {
        console.error("Erro ao buscar fornecedores:", error);
    }
};



const handleUserRegistered = () => {
    fetchFornecedores(currentPage.value);
};

const selectRow = (id) => {
    selectedId.value = id;
    emit('update:modelValue', id);
};

onMounted(() => {
    fetchFornecedores(currentPage.value);
    window.addEventListener('fornecedor-registered', handleUserRegistered);
});

onBeforeUnmount(() => {
    window.removeEventListener('fornecedor-registered', handleUserRegistered);
});

const updatePage = (page) => {
    {
        if (page > 0) {
            fetchFornecedores(page);
        }

    }
};
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

#selectionPage-container {
    display: flex;
    margin-top: 10px;
}

#page-label {
    margin: 0 10px;
}

#nome-coluna {
    width: 45%;
}

#cnpj-coluna {
    width: 25%;
}

#id-coluna {
    width: 7%;
}

tr:hover {
    background-color: #2889e44f;
}


tr.selected {
    background-color: #2889e477;
}


</style>