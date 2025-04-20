<template>
  <div>
    <table>
      <thead>
        <tr>
          <th id="id-column">Id</th>
          <th>Nome</th>
          <th id="tipo-column">Tipo</th>
          <th id="data-lancamento-column">Data Lanç.</th>
          <th id="unidade-column">Und</th>
          <th id="valor-unitario-column">Valor Unitário</th>
          <th id="qtd-column">Qtd</th>
          <th id="valor-total-column">Valor Total</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="item in paginatedItems" :key="item.Id">
          <td>{{ item.id }}</td>
          <td>{{ item.nome }}</td>
          <td>{{ item.tipo }}</td>
          <td>{{ item.dataLancamento }}</td>
          <td>{{ item.unidade }}</td>
          <td>R${{ item.valorUnitario }}</td>
          <td>{{ item.qtd }}</td>
          <td>R${{ item.valorUnitario * item.qtd }}</td>
        </tr>
      </tbody>
    </table>
    <pagination :data="items" :limit="15" @pagination-change-page="updatePage"></pagination>
    <div v-if="items.length > perPage" id="selectionPage-container">
      <h1 @click="updatePage(currentPage - 1)">&lt;</h1>
      <h1 id="page-label">{{ currentPage }}</h1>
      <div @click="updatePage(currentPage + 1)">
        <h1>&gt;</h1>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref, computed, onMounted, onBeforeUnmount } from 'vue';
import Pagination from 'laravel-vue-pagination';
import { useRouter } from 'vue-router';
import api from "../services/axios";

const props = defineProps({
  etapaObraId: {
    type: Number,
    required: true
  }
});



const currentPage = ref(1);
const perPage = 15;
const items = ref([]);
const etapaId = ref();

const fetchItens = async (page) => {
  try {
    const response = await api.get(`/obra/etapa/item/EtapaObra/${etapaId.value}`);

    console.log(response.data, etapaId.value);

    items.value = response.data.map(item => ({
      id: item.id,
      qtd: item.quantidade,
      dataLancamento: item.dataLancamento,
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

const paginatedItems = computed(() => {
  const start = (currentPage.value - 1) * perPage;
  const end = start + perPage;
  return items.value.slice(start, end);
});

function updatePage(page) {
  if (page >= 1 && page <= Math.ceil(items.length / perPage)) {
    currentPage.value = page;
  }
}

const handlerEtapaChanged = (event) => {
  items.value = [];
  currentPage.value = 1;
   etapaId.value = event.detail.etapaId;
  fetchItens(currentPage.value);
};

onMounted(() => {
  window.addEventListener('etapa-updated', handlerEtapaChanged);
});

onBeforeUnmount(() => {
  window.removeEventListener('etapa-updated', handlerEtapaChanged);
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

#tipo-column {
  width: 8%;
}

#data-lancamento-column {
  width: 7%;
}

#unidade-column {
  width: 5%;
}

#valor-unitario-column {
  width: 10%;
}

#qtd-column {
  width: 10%;
}

#valor-total-column {
  width: 10%;
}

#id-column {
  width: 5%;
}
</style>