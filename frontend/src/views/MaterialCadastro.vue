<script setup>
     import TopLabelTextBox from '../components/TopLabelTextBox';
    import Button from '../components/Button';
    import Searchbar from '../components/Searchbar.vue';
    import FilterSelector from '../components/FilterSelector.vue';
    import TabelaMateriais from '../components/TabelaMateriais.vue';
    import ButtonRed from '../components/ButtonRed.vue';
    components: { Searchbar,Button,TopLabelTextBox,FilterSelector};
</script>

<template>
    <div id="main-container">
        <h2>Cadastro/Material</h2>

        <div id="top-container">
            <div id="searchbar-container">
                <searchbar/>
            </div>
            <div id="filter-container">
                <FilterSelector label="Ordenar por:"/>
            </div>
        </div>

        <div id="bottom-container">
            <div id="table-container">
                <TabelaMateriais/>
            </div>
            <div id="form-container">
                <h1>Cadastro Material</h1>
                <TopLabelTextBox label="Nome"/>
                <TopLabelTextBox label="Unidade"/>
                <TopLabelTextBox label="Preço"/>
                <Button label="Cadastrar"/>
                <ButtonRed class="ButtonRed" label="Excluir"/>
            </div>
        </div>
    </div>
</template>

<style scoped>
    #top-container{
        display: flex;
        align-items: center;
        height: 40px;
        margin-top: 50px;
        max-width: 1800px;
        margin-left: 20px;
    }

    #bottom-container{
        display: flex;
        height: 750px;
        margin-top:50px;
        margin-left: 20px;
    }

    #searchbar-container{
        height: 100%;
        width: 700px;
        
    }

    #filter-container{
        height: 100%;
        width: 20%;
        margin-left: 100px;
        margin-top:4px;
    }

    #table-container{
        height: 100%;
        width: 70%;
    }

    #form-container{
        display: flex;
        flex-direction: column;
        height: 60%;
        width: 20%;
        margin-left: 2.5%;
        margin-top: 4%;
        text-align: center;
        font-size: 0.65em;
        justify-content: space-between;
    }
</style>