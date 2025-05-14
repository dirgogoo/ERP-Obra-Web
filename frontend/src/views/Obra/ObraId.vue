<script setup>
  import { ref } from "vue";
  import { useRouter } from 'vue-router';

  const route = useRouter();

  const menuOptions = ["Geral", "Etapas", "Itens" ,"Info","Editar"];
  const selected = ref("Geral");
  const obrainfo = ref([]);
  const nome = ref("");
  const status = ref("");
  const isLoading = ref(true);

  

  const currentPath = route.currentRoute.value.params.id;

  import { onMounted } from "vue";
import api from "@/services/axios";

const roles = ref([]);
    const isLoadingRoles = ref(true);
    onMounted(() => {
        // Fetch the roles when the component is mounted
        api.get('user/roles').then(response => {
            roles.value = response.data;
            isLoadingRoles.value = false;
            console.log(roles.value[1])
            console.log(roles.value.some(role => role === 'ADMIN'));
        }).catch(() => {
            isLoadingRoles.value = false;
        });
    });

  onMounted(async () => {
    try {
      const response = api.get("/obra/"+currentPath);
      const obra = (await response).data;
      nome.value = obra.nome;
      status.value = obra.status;
      obrainfo.value = obra;
      console.log(obra);
    console.log("loaded obra")
      isLoading.value = false;
    } catch (error) {
      console.error("Error fetching obra data:", error);
    }
    toRouteId(selected.value);
  });

  function selectItem(item) {
    selected.value = item; 
    toRouteId(item);
  }
  const toRouteId = (item) => {
    if (item.toLowerCase() == "editar"){
      route.replace("/app/obra/"+item.toLowerCase()+ '/' + currentPath);
    }else {
    route.replace("/app/obra/"+currentPath+ '/' +item.toLowerCase());
  };

}
</script>


<template>
  <div  id="main-container">
    <h2>Obra/{{ $route.fullPath.split("obra/")[1] }}</h2>
    <div></div>
    <div id="info-container">
     
      <div id="info-grid">
        <h1>{{nome}}</h1>
        <h2 id="info-status">Status: {{status}}</h2>
      </div>
    </div>
    <ul>
      <li
        v-for="item in menuOptions"
        :key="item"
        :class="{ active: selected === item }"
        @click="selectItem(item)" v-show="item != 'Editar' || roles.some(role => role === 'ADMIN')">
        {{ item }}
      </li>
    </ul>
    <div id="content-container">
        <router-view :obra="obrainfo"/>
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
