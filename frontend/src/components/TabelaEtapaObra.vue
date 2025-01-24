<template>
    <div>
      <table>
        <thead>
          <tr>
            <th id="id-column">Id</th>
            <th>Nome</th>
            <th id="valor-total-column">Valor Total</th>
            <th id="despesas-column">Despesas</th>
            <th id="saldo-column">Saldo</th>
            <th id="inicio-column">Início</th>
            <th id="final-column">Final</th>
            <th id="dias-res-column">Dias Res.</th>
            <th id="status-column">Status</th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="etapa in paginatedEtapas" :key="etapa.Id" @click="toRouteId(etapa.Id)">
            <td>{{ etapa.Id }}</td>
            <td>{{ etapa.Nome }}</td>
            <td>R${{ etapa.ValorTotal }}</td>
            <td>R${{ etapa.Despesas }}</td>
            <td>R${{ etapa.Saldo }}</td>
            <td>{{ etapa.Inicio }}</td>
            <td>{{ etapa.Final }}</td>
            <td>{{ etapa.DiasRes }}</td>
            <td>{{ etapa.Status }}</td>
          </tr>
        </tbody>
      </table>
      <pagination
        :data="etapas"
        :limit="15"
        @pagination-change-page="updatePage"
      ></pagination>
      <div v-if="etapas.length > perPage" id="selectionPage-container">
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
    name: 'TabelaEtapa',
    components: {
      Pagination
    },
    setup() {
      const router = useRouter();
      const etapas = ref([
        { Id: 1, Nome: 'Etapa 1', ValorTotal: 1000, Despesas: 200, Saldo: 800, Inicio: '2023-01-01', Final: '2023-01-10', DiasRes: 9, Status: 'Em andamento' },
        { Id: 2, Nome: 'Etapa 2', ValorTotal: 1500, Despesas: 500, Saldo: 1000, Inicio: '2023-02-01', Final: '2023-02-15', DiasRes: 14, Status: 'Não Iniciado' }, 
        { Id: 3, Nome: 'Etapa 3', ValorTotal: 2000, Despesas: 800, Saldo: 1200, Inicio: '2023-03-01', Final: '2023-03-20', DiasRes: 19, Status: 'Concluído' },
      { Id: 4, Nome: 'Etapa 4', ValorTotal: 2500, Despesas: 1000, Saldo: 1500, Inicio: '2023-04-01', Final: '2023-04-25', DiasRes: 24, Status: 'Atrasado' },
      { Id: 5, Nome: 'Etapa 5', ValorTotal: 3000, Despesas: 1200, Saldo: 1800, Inicio: '2023-05-01', Final: '2023-05-30', DiasRes: 29, Status: 'Em andamento' },
      { Id: 6, Nome: 'Etapa 6', ValorTotal: 3500, Despesas: 1500, Saldo: 2000, Inicio: '2023-06-01', Final: '2023-06-20', DiasRes: 19, Status: 'Não Iniciado' },
      { Id: 7, Nome: 'Etapa 7', ValorTotal: 4000, Despesas: 1800, Saldo: 2200, Inicio: '2023-07-01', Final: '2023-07-25', DiasRes: 24, Status: 'Concluído' },
      { Id: 8, Nome: 'Etapa 8', ValorTotal: 4500, Despesas: 2000, Saldo: 2500, Inicio: '2023-08-01', Final: '2023-08-30', DiasRes: 29, Status: 'Atrasado' },
      { Id: 9, Nome: 'Etapa 9', ValorTotal: 5000, Despesas: 2200, Saldo: 2800, Inicio: '2023-09-01', Final: '2023-09-20', DiasRes: 19, Status: 'Em andamento' },
      { Id: 10, Nome: 'Etapa 10', ValorTotal: 5500, Despesas: 2500, Saldo: 3000, Inicio: '2023-10-01', Final: '2023-10-25', DiasRes: 24, Status: 'Não Iniciado' },
      { Id: 11, Nome: 'Etapa 11', ValorTotal: 6000, Despesas: 2800, Saldo: 3200, Inicio: '2023-11-01', Final: '2023-11-30', DiasRes: 29, Status: 'Concluído' },
      { Id: 12, Nome: 'Etapa 12', ValorTotal: 6500, Despesas: 3000, Saldo: 3500, Inicio: '2023-12-01', Final: '2023-12-20', DiasRes: 19, Status: 'Atrasado' },
      { Id: 13, Nome: 'Etapa 13', ValorTotal: 7000, Despesas: 3200, Saldo: 3800, Inicio: '2024-01-01', Final: '2024-01-25', DiasRes: 24, Status: 'Em andamento' },
      { Id: 14, Nome: 'Etapa 14', ValorTotal: 7500, Despesas: 3500, Saldo: 4000, Inicio: '2024-02-01', Final: '2024-02-15', DiasRes: 14, Status: 'Não Iniciado' },
        // Adicione mais etapas conforme necessário
      ]);
      const currentPage = ref(1);
      const itemsPerPage = 15;
  
      const paginatedEtapas = computed(() => {
        const start = (currentPage.value - 1) * itemsPerPage;
        const end = start + itemsPerPage;
        return etapas.value.slice(start, end);
      });
  
      function updatePage(page) {
        if (page >= 1 && page <= Math.ceil(etapas.value.length / perPage.value)){
                currentPage.value = page;
            }
      }
  
      const toRouteId = (id) => {
        //router.push(`/etapa/${id}`);
      };
  
      return {
        etapas,
        currentPage,
        itemsPerPage,
        paginatedEtapas,
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

#valor-total-column{
    width: 10%;
}

#despesas-column{
    width: 10%;
}

#saldo-column{
    width: 10%;
}

#inicio-column{
    width: 10%;
}

#final-column{
    width: 10%;
}

#dias-res-column{
    width: 5%;
}

#status-column{
    width: 10%;
}

#id-column{
    width: 5%;
}


</style>