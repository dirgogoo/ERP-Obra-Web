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
                <tr v-for="item in paginatedItens" :key="item.Id"  :class="{selected: item.id === selectedId}" @click="selectRow(item.id)">
                    <td>{{ item.id }}</td>
                    <td>{{ item.item.name }}</td>
                    <td>{{ item.item.tipo }}</td>
                    <td>{{ item.item.unidade }}</td>
                    <td>{{ item.quantidade }}</td>
                    <td>R${{ item.item.valor * item.quantidade}}</td>
                </tr>
            </tbody>
        </table>
        <pagination :data="itens" :limit="15" @pagination-change-page="updatePage"></pagination>
        <div v-if="itens.length > perPage" id="selectionPage-container">
            <h1 @click="updatePage(currentPage - 1)">&lt;</h1>
            <h1 id="page-label">{{ currentPage }}</h1>
            <div @click="updatePage(currentPage + 1)">
                <h1>&gt;</h1>
            </div>
        </div>
    </div>
</template>

<script setup>
import { ref, computed, onMounted, watch, onBeforeUnmount} from 'vue';
import Pagination from 'laravel-vue-pagination';
import api from "../services/axios";

const props = defineProps({
    itenss: {
        type: Array,
        required: true
    },
    etapaId: {
        type: Number,
        required: true
    }
});

const emit = defineEmits(['update:selectedID']);

const itens = ref([]);
const currentPage = ref(1);
const perPage = ref(15);
const selectedId = ref(null);

itens.value = props.itenss;

const selectRow = (id) => {
    selectedId.value = id;
    emit('update:selectedID', id);
};

watch(() => props.itenss, () => {
    itens.value = props.itenss;
});




const paginatedItens = computed(() => {
    const start = (currentPage.value - 1) * perPage.value;
    const end = start + perPage.value;
    console.log("Pagination Updated")
    return itens.value.slice(start, end);
});

const updatePage = (page) => {
    if (page >= 1 && page <= Math.ceil(itens.value.length / perPage.value))
        currentPage.value = page;
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

#coluna-qtd {
    width: 8%;
}

#coluna-preco {
    width: 15%;
}

tr:hover {
    background-color: #2889e44f;
}


tr.selected {
    background-color: #2889e477;
}

</style>