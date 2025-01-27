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
        <tr v-for="item in paginatedItems" :key="item.Id" >
          <td>{{ item.Id }}</td>
          <td>{{ item.Nome }}</td>
          <td>{{ item.Tipo }}</td>
          <td>{{ item.DataLancamento }}</td>
          <td>{{ item.Unidade }}</td>
          <td>R${{ item.ValorUnitario }}</td>
          <td>{{ item.Qtd }}</td>
          <td>R${{ item.ValorTotal }}</td>
        </tr>
      </tbody>
    </table>
    <pagination
      :data="items"
      :limit="15"
      @pagination-change-page="updatePage"
    ></pagination>
    <div v-if="items.length > perPage" id="selectionPage-container">
      <h1 @click="updatePage(currentPage - 1)">&lt;</h1>
      <h1 id="page-label">{{currentPage}}</h1>
      <div @click="updatePage(currentPage + 1)"><h1>&gt;</h1></div>
    </div>
  </div>
</template>

<script>
import { ref, computed } from 'vue';
import Pagination from 'laravel-vue-pagination';
import { useRouter } from 'vue-router';

export default {
  name: 'TabelaItem',
  components: {
    Pagination
  },
  setup() {
    const router = useRouter();
    const items = ref([
      { Id: 1, Nome: 'Item 1', Tipo: 'Material', DataLancamento: '01/01/23', Unidade: 'kg', ValorUnitario: 10, Qtd: 100, ValorTotal: 1000 },
      { Id: 2, Nome: 'Item 2', Tipo: 'Serviço', DataLancamento: '02/01/23', Unidade: 'h', ValorUnitario: 50, Qtd: 20, ValorTotal: 1000 },
      { Id: 3, Nome: 'Item 3', Tipo: 'Material', DataLancamento: '03/01/23', Unidade: 'm', ValorUnitario: 5, Qtd: 200, ValorTotal: 1000 },
      // Adicione mais itens conforme necessário
    ]);
    const currentPage = ref(1);
    const itemsPerPage = 15;

    const paginatedItems = computed(() => {
      const start = (currentPage.value - 1) * itemsPerPage;
      const end = start + itemsPerPage;
      return items.value.slice(start, end);
    });

    function updatePage(page) {
      if (page >= 1 && page <= Math.ceil(items.value.length / perPage.value))
      currentPage.value = page;
    }

    return {
      items,
      currentPage,
      itemsPerPage,
      paginatedItems,
      updatePage,
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

#tipo-column{
    width: 8%;
}

#data-lancamento-column{
    width: 7%;
}

#unidade-column{
    width: 5%;
}

#valor-unitario-column{
    width: 10%;
}

#qtd-column{
    width: 10%;
}

#valor-total-column{
    width: 10%;
}

#id-column{
    width: 5%;
}

</style>