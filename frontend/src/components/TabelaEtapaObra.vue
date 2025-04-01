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
            <td>{{ etapa.id }}</td>
            <td>{{ etapa.etapa.name }}</td>
            <td>R${{ etapa.price }}</td>
            <td>R${{ etapa.price - etapa.saldo }}</td>
            <td>
              <a style="color: red;" v-if="etapa.saldo<0">-R$ {{ Math.abs(etapa.saldo) }}</a>
              <a style="color: green;" v-else>R$ {{ etapa.saldo }}</a>
              </td>
            <td>{{ etapa.dataInicio }}</td>
            <td>{{ etapa.deadline }}</td>
            <td><a style="color: red;" v-if="subtrairDatas(etapa.dataInicio, etapa.deadline )<0">{{ subtrairDatas(etapa.dataInicio, etapa.deadline )}}</a>
              <a style="color: green;" v-else>{{ subtrairDatas(etapa.dataInicio, etapa.deadline ) }}</a></td>
            <td><a :class="{
              blue : etapa.status == 'NAOINICIADO',
              green : etapa.status == 'CONCLUIDO',
              red : etapa.status == 'ATRASADO',
              yellow : etapa.status == 'ANDAMENTO'
            }">{{ etapa.status }}</a></td>
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
  
  <script setup>
  import { ref, computed } from 'vue';
  import Pagination from 'laravel-vue-pagination';
  import { useRouter } from 'vue-router';


  const props = defineProps({
    etapas: {
      type: Array,
      required: true
    }
  });
  
  const router = useRouter();
  const currentPage = ref(1);
  const itemsPerPage = 15;


  
  const paginatedEtapas = computed(() => {
    const start = (currentPage.value - 1) * itemsPerPage;
    const end = start + itemsPerPage;
    return props.etapas.slice(start, end);
  });
  
  function updatePage(page) {
    if (page >= 1 && page <= Math.ceil(props.etapas.length / itemsPerPage)) {
      currentPage.value = page;
    }
  }
  
  const toRouteId = (id) => {
   // router.push(`/etapa/${id}`);
  };

const subtrairDatas = (data1, data2) => {
  console.log(data1, data2)
    //11/03/2024 para 2024-03-11
  const [day1, month1, year1] = data1.split('/');
  const [day2, month2, year2] = data2.split('/');
  const data1N = `${year1}-${month1}-${day1}`;
  const data2N = `${year2}-${month2}-${day2}`;

// Converte as strings de data para objetos Date
  const data1Date = new Date(data1N);
  const data2Date = new Date(data2N);
  
  const diffTime = data1Date - data2Date;
  const diffDays = Math.ceil(diffTime / (1000 * 60 * 60 * 24));

  return diffDays;
}


  </script>

  

<style scoped>
.blue{
    color: #2888E4;
}

.green{
    color: green;
}

.red{
    color: red;
}

.yellow{
    color: yellow;
}

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