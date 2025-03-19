<script setup>
     import TopLabelTextBox from '../../components/TopLabelTextBox';
    import Button from '../../components/Button';
    import ButtonRed from '../../components/ButtonRed';
    import Searchbar from '../../components/Searchbar.vue';
    import TabelaEtapas from '../../components/TabelaEtapas.vue';
    import { ref } from 'vue';
    import api from '../../services/axios';


    const nome = ref('');

    const search = ref('');

    const cadastrar = async () => {
        try{
            const response = await api.post("/etapa", {
                name: nome.value,
            });
            const event = new CustomEvent('etapa-registered');
            window.dispatchEvent(event);
        } catch (error) {
            console.error("Erro ao cadastrar etapa:", error);
        }
    }

</script>

<template>
    <div id="main-container">
        <h2>Cadastro/Etapa</h2>

        <div id="top-container">
            <div id="searchbar-container">
                <searchbar v-model="search"/>
            </div>
        </div>

        <div id="bottom-container">
            <div id="table-container">
                <TabelaEtapas :search="search"/>
            </div>
            <div id="form-container">
                <h1>Cadastro Etapa</h1>
                <TopLabelTextBox label="Nome" v-model="nome"/>
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

    #table-container{
        height: 100%;
        width: 70%;
    }

    #form-container{
        display: flex;
        flex-direction: column;
        height: 40%;
        width: 20%;
        margin-left: 2.5%;
        margin-top: 5%;
        text-align: center;
        font-size: 0.65em;
        justify-content: space-between;
        gap: 30px;
    }
    
    .button{
        height: 60px;
    }

    #button-container{
        display: flex;
        flex-direction: column;
        justify-content: space-between;
        gap: 30px;
    }

    
</style>