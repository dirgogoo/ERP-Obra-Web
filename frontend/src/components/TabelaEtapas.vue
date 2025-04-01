<template>
    <div>
        <table>
            <thead>
                <tr>
                    <th id="numero-coluna">ID</th>
                    <th>Nome</th>
                </tr>
            </thead>
            <tbody>
                <tr v-for="etapa in etapas" :key="etapa.id" :class="{selected: etapa.id === selectedId}" @click="selectRow(etapa.id)">
                    <td>{{ etapa.id }}</td>
                    <td>{{ etapa.nome }}</td>
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
import { ref, computed, onMounted, onBeforeUnmount, watch } from 'vue';
import api from "../services/axios";
import { EmitFlags } from 'typescript';


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


watch(() => props.search, () => {
    fetchEtapas(currentPage.value);
});

const etapas = ref([]);
const currentPage = ref(1);
const perPage = ref(16);
const selectedId = ref(null);



const fetchEtapas = async (page) => {
    try {
        const response = await api.get('/etapa', {
            params: {
                page: page - 1,
                size: perPage.value,
                sort: 'id,desc',
                search: props.search
            }
        });

        etapas.value = response.data.content.map(client => ({
            id: client.id,
            nome: client.name,
            cnpj: client.cnpj,
        }));

        currentPage.value = page;

    } catch (error) {
        console.error("Erro ao buscar etapas:", error);
    }
};

const selectRow = (id) => {
    selectedId.value = id;
    emit('update:modelValue', id);
};

const handleUserRegistered = () => {
    fetchEtapas(currentPage.value);
};

onMounted(() => {
    fetchEtapas(currentPage.value);
    window.addEventListener('etapa-registered', handleUserRegistered);
});

onBeforeUnmount(() => {
    window.removeEventListener('etapa-registered', handleUserRegistered);
});

const updatePage = (page) => {
    {
        if (page > 0) {
            fetchEtapas(page);
        }

    }
};

</script>


<style scoped>
#selectionPage-container {
    display: flex;
    margin-top: 10px;
}

#page-label {
    margin: 0 10px;
}

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

#numero-coluna {
    width: 7%;
}

tr:hover {
    background-color: #2889e44f;
}


tr.selected {
    background-color: #2889e477;
}


</style>