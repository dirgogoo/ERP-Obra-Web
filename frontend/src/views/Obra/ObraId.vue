<script setup>
  import { ref } from "vue";
  import { useRouter } from 'vue-router';

  const route = useRouter();

  const menuOptions = ["Geral", "Etapas", "Itens","Solicitações" ,"Info"];
  const selected = ref("Geral");
  
  const currentPath = route.currentRoute.value.params.id;



  function selectItem(item) {
    selected.value = item; 
    toRouteId(item);
  }
  const toRouteId = (item) => {
    route.replace("/app/obra/"+currentPath+ '/' +item.toLowerCase());
  };

  toRouteId(selected.value);
</script>


<template>
  <div id="main-container">
    <h2>Obra/{{ $route.params.id }}</h2>
    <div id="info-container">
     
      <div id="info-grid">
        <h1>Restauração Condominio Nova Vila</h1>
        <!--<div id="grid-wrapper">
          <h2>Cliente: Diego</h2>
          <h2>Código: {{ $route.params.id }}</h2>
          <h2>Criação: 22/01/2025</h2>
          <h2>Inicio: 22/01/2025</h2>
        </div>-->
        <h2 id="info-status">Status: Não Iniciado</h2>
      </div>
    </div>
    <ul>
      <li
        v-for="item in menuOptions"
        :key="item"
        :class="{ active: selected === item }"
        @click="selectItem(item)">
        {{ item }}
      </li>
    </ul>
    <div id="content-container">
        <router-view />
    </div>
    
  </div>
</template>

<style scoped>
h1 {
  margin-top: 20px;
  font-size: 3em;
}
#info-grid {
  display: flex;
  justify-content: space-between;
  margin-right: 20px;
}
#grid-wrapper {
  width: 60%;
  display: inline grid;
  grid-template-columns: 1fr 1fr;
  grid-template-rows: 1fr 1fr;
  gap: 10px;
  margin-top: 20px;
  font-size: 0.8em;
}
#info-status {
  align-self: flex-end;
  font-size: 1.6em;
}
ul {
  list-style-type: none;
  width: 100%;
  border-bottom: 2px solid black;
  font-size: 1.4em;
  padding-top: 20px;
  padding-bottom: 20px;
}
li {
  float: left;
  margin-right: 4%;
  border: none;
  padding-left: 10px;
  padding-right: 10px;
}

li:hover {
  cursor: pointer;
  opacity: 0.7;
}
.active {
  border-bottom: 2px solid #2888E4;
}

#content-container {
  width: 100%;
  height: 70vh;
  margin-top: 2vh;
}
</style>
