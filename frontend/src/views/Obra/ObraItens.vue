<script setup>
    import FilterSelector from '@/components/FilterSelector.vue';
    import TabelaItemObra from '@/components/TabelaItemObra.vue';
    import Button from '@/components/Button.vue';
</script>

<template>
    <div id="main-container">
        <div id="form-container">
            <FilterSelector class="FilterSelector" label="Etapa Itens:"/>
            <FilterSelector class="FilterSelector"  label="Ordenar por:"/>
            <Button id="button" label="Gerenciar Itens"/>
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
        gap: 2%;
    }

    #button{
        margin-left: 8%;
        font-size: 1.7em;;
    }

    .FilterSelector{
        margin-top: 13px;
    }

    #table-container{
        margin-top: 20px;
        height: 85%;
        width: 98%;
    }

</style>