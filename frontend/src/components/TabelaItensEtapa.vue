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
                    <td>{{ item.id }}</td>
                    <td>{{ item.nome }}</td>
                    <td>{{ item.tipo }}</td>
                    <td>{{ item.unidade }}</td>
                    <td>{{ item.qtd }}</td>
                    <td>R${{ item.valorUnitario * item.qtd }}</td>
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
import { ref, computed, onMounted, watch } from 'vue';
import Pagination from 'laravel-vue-pagination';
import api from "../services/axios";

const props = defineProps({
    modelValue: {
        type: Array,
        required: true
    },
    etapaId: {
        type: Number,
        required: true
    }
});

const emit = defineEmits(['update:modelValue']);

const itens = ref([]);
const toSaveItens = ref([]);
const currentPage = ref(1);
const perPage = ref(15);

const fetchItens = async (page) => {
    try {
        const response = await api.get(`/obra/etapa/item/EtapaObra/${props.etapaId}`, {
            params: {
                page: page - 1,
                size: perPage.value
            }
        });

        itens.value = response.data.content.map(item => ({
            id: item.id,
            qtd: item.quantidade,
            unidade: item.item.unidade,
            nome: item.item.name,
            tipo: item.item.tipo,
            valorUnitario: item.item.valor,
        }));

        currentPage.value = page;
    } catch (error) {
        console.error("Erro ao buscar itens:", error);
    }
};

watch(props.modelValue, (newVal, oldVal) => {
    console.log(newVal, oldVal)
    const addedItem = newVal[newVal.length - 1];
    const item = itens.value.find((item) => item == addedItem.item);
    //if (item) {
      //  itens.value.push({
      //      id: item.id,
      //      qtd: addedItem.quantidade,
      //      unidade: addedItem.item.und,
      //      nome: addedItem.item.nome,
      //      tipo: addedItem.item.tipo,
       //     valorUnitario: addedItem.item.preço,
       // });
       // newVal[newVal.length - 1].id = item.id;
       // itens.value.slice(itens.value.indexOf(item), 1);
   // } else { */
        console.log(addedItem)
        itens.value.push({
            id: "-",
            qtd: addedItem.quantidade,
            unidade: addedItem.item.und,
            nome: addedItem.item.nome,
            tipo: addedItem.item.tipo,
            valorUnitario: addedItem.item.preço,
        });
   // };
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

#coluna-qtd {
    width: 8%;
}

#coluna-preco {
    width: 15%;
}
</style>