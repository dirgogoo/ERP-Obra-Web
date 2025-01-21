<script setup>
    import TabelaObra from '@/components/TabelaObra.vue';
    import Button from '../../components/Button';
    import Searchbar from '../../components/Searchbar.vue';
    import FilterSelector from '../../components/FilterSelector.vue';
</script>

<template>
    <div id="main-container">
        <h2>Obra</h2>

        <div id="top-container">
            <div id="searchbar-container">
                 <searchbar/>
            </div>
            <div class="filter-container">
                <FilterSelector label="Ordenar por:"/>
            </div>
            <div class="filter-container">
                <FilterSelector label="Filtrar por:"/>
            </div>
            <div id="button-container">
                <Button label="Nova Obra"/>

            </div>
        </div>

        <div id="bottom-container">
            <div id="table-container">
                <TabelaObra/>
            </div>
        </div>
    </div>
</template>

<style scoped>
 #top-container{
        display: flex;
        align-items: center;
        height: 10vh;
        margin-top: 20px;
        max-width: 1800px;
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
        margin-left: 50px;
    }
    
</style>