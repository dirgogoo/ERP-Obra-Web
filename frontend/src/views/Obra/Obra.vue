<script setup>
    import TabelaObra from '@/components/TabelaObra.vue';
    import Button from '../../components/Button';
    import Searchbar from '../../components/Searchbar.vue';
    import FilterSelector from '../../components/FilterSelector.vue';
    import { onMounted, ref, watch } from 'vue';
    import api from '@/services/axios';


    const roles = ref(["ASD"]);
    const search = ref('');
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

</script>

<template>
    <div id="main-container">
        <h2>Obra</h2>

        <div id="top-container">
            <div id="searchbar-container">
                 <searchbar v-model="search"/>
            </div>
            <div class="filter-container">
                <FilterSelector label="Ordenar por:"/>
            </div>
            <div class="filter-container">
                <FilterSelector label="Filtrar por:"/>
            </div>
            <RouterLink to="/app/obra/criar" id="button-container" v-show="!isLoadingRoles && roles.some(role => role === 'ADMIN')">
                
                <Button  label="Nova Obra"/>
            </RouterLink>
        </div>

        <div id="bottom-container">
            <div id="table-container">
                <TabelaObra :search="search"/>
            </div>
        </div>
    </div>
</template>

<style scoped>
 #top-container{
        display: flex;
        align-items: center;
        height: 10vh;
        width: 100%;
        margin-top: 20px;
        margin-left: 20px;
    }

    #bottom-container{
        display: flex;
        height: 65vh;
        margin-top:30px;
        margin-left: 20px;
    }

    #table-container{
        height: 100%;
        width: 100%;
    }

    #searchbar-container{
        width:33vw;
    }

    .filter-container{
        width: 20%;
        margin-left: 50px;
    }

    #button-container{
        font-size: 0.5em;
        width: 15%;
        height: 60px;
        margin-left: 50px;
        justify-self: flex-end;
    }
    
</style>