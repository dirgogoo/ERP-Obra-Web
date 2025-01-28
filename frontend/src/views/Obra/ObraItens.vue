<script setup>
    import FilterSelector from '@/components/FilterSelector.vue';
    import TabelaItemObra from '@/components/TabelaItemObra.vue';
    import Button from '@/components/Button.vue';
    import { useRouter } from 'vue-router';

    const route = useRouter();

    const currentPath = route.currentRoute.value.params.id;

  const toRouteId = (item) => {
    route.replace("/app/obra/"+currentPath+ '/itens/' +item.toLowerCase());
  };
</script>

<template>
    <div id="main-container">
        <div id="form-container">
            <FilterSelector class="FilterSelector" label="Etapa Itens:"/>
            <FilterSelector class="FilterSelector"  label="Ordenar por:"/>
            <Button id="button" label="Gerenciar Itens" @click="toRouteId('gerenciar')"/>
        </div>
        <div id="table-container">
            <TabelaItemObra/>
        </div>
    </div>
</template>

<style scoped>
    #main-container{
        height: 100%;
        width: 100%;
        margin-left: 0.5%;
    }
    #form-container{
        height: 10%;
        width: 98%;
        display: flex;
        justify-content: center;
        font-size: 0.9em;
        gap: 2%;
    }

    #button{
        margin-left: 8%;
        font-size: 1.7em;;
        height: 60px;
        width: 60%;
    }

    .FilterSelector{
        margin-top: 10px;
    }

    #table-container{
        margin-top: 20px;
        height: 85%;
        width: 98%;
    }

</style>