<script setup>
     import TopLabelTextBox from '../../components/TopLabelTextBox';
    import Button from '../../components/Button';
    import ButtonRed from '../../components/ButtonRed';
    import Searchbar from '../../components/Searchbar.vue';
    import FilterSelector from '../../components/FilterSelector.vue';
    import TabelaMateriais from '../../components/TabelaMateriais.vue';
    import { ref } from 'vue';
    import api from '../../services/axios';


    const nome = ref('');
    const unidade = ref('');
    const valor = ref('');

    const search = ref('');

    const cadastrar = async () => {
        try{
            const response = await api.post("/item", {
                name: nome.value,
                unidade: unidade.value,
                valor: valor.value,
                tipo : 1,
            });
            const event = new CustomEvent('material-registered');
            window.dispatchEvent(event);
        } catch (error) {
            console.error("Erro ao cadastrar item:", error);
        }
    }

</script>

<template>
    <div id="main-container">
        <h2>Cadastro/Material</h2>

        <div id="top-container">
            <div id="searchbar-container">
                <searchbar v-model="search"/>
            </div>
            <div id="filter-container">
                <FilterSelector label="Ordenar por:"/>
            </div>
        </div>

        <div id="bottom-container">
            <div id="table-container">
                <TabelaMateriais :search="search"/>
            </div>
            <div id="form-container">
                <h1>Cadastro Material</h1>
                <TopLabelTextBox label="Nome" v-model="nome"/>
                <TopLabelTextBox label="Unidade"  v-model="unidade"/>
                <TopLabelTextBox label="Preço"  v-model="valor"/>
                <div id="button-container">
                    <Button label="Cadastrar" class="button" @click="cadastrar()"/>
                    <ButtonRed class="button" label="Excluir"/>
                </div>
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

    #button-container{
        display: flex;
        flex-direction: column;
        justify-content: space-between;
        gap: 30px;
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

    .button{
        height: 60px;
    }

</style>