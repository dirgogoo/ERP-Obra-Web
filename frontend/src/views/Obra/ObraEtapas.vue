<script setup>
    import TabelaEtapaObra from '@/components/TabelaEtapaObra.vue';
</script>

<template>
    <div id="main-container">
        <div id="table-container">
            <TabelaEtapaObra/>
        </div>
    </div>
</template>

<style scoped>
    #main-container{
        height: 100%;
        width: 100%;
    }

    #table-container{
        margin: auto;
        margin-top: 20px;
        height: 100%;
        width: 98%;
    }

</style>