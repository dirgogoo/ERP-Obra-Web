<script setup>
     import TopLabelTextBox from '../../components/TopLabelTextBox';
    import Button from '../../components/Button';
    import ButtonRed from '../../components/ButtonRed';
    import Searchbar from '../../components/Searchbar.vue';
    import TopLabelSelect from '../../components/TopLabelSelect';
    import FilterSelector from '../../components/FilterSelector.vue';
    import TabelaClientes from '../../components/TabelaClientes.vue';
    import { ref,onMounted } from 'vue';
    import api from '../../services/axios';
import TabelaFornecedores from '@/components/TabelaFornecedores.vue';

    const selectedId = ref(null);

    const nome = ref('');
    const cnpj = ref('');

    const regioes = ref([]);
    const regioeSelecionadaSelecao = ref();
    const regioeSelecao = ref([]);

    const search = ref('');

    
onMounted(() => {
    api.get('/regiao/all').then(response => {
        regioes.value = response.data;
        for (let i = 0; i < regioes.value.length; i++) {
           regioeSelecao.value.push(regioes.value[i].nome);
        }
    });
});


    const cadastrar = async () => {
        
        if (!nome.value || !cnpj.value || !regioeSelecionadaSelecao.value) {
            alert("Nome e CNPJ são obrigatórios.");
            return;
        }
        try{
            const founded = regioes.value.find((regiao) => regiao.nome === regioeSelecionadaSelecao.value);
            console.log(founded.id)
            const response = await api.post("/fornecedor", {
                nome: nome.value,
                cnpj: cnpj.value,
                regiao : {"id" : founded.id},
            });
            const event = new CustomEvent('fornecedor-registered');
            window.dispatchEvent(event);
        } catch (error) {
            console.error("Erro ao cadastrar Fornecedor:", error);
        }
    }

    const deleteE = async () => {
    try {

        const response = await api.delete(`/fornecedor/${selectedId.value}`);
        const event = new CustomEvent('fornecedor-registered');
        window.dispatchEvent(event);
    } catch (error) {
        console.error("Erro ao deletar fornecedor:", error);
    }
}


</script>

<template>
    <div id="main-container">
        <h2>Cadastro/Fornecedor</h2>

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
                <TabelaFornecedores :search="search" v-model="selectedId"/>
            </div>
            <div id="form-container">
                <h1>Cadastro Fornecedor</h1>
                <TopLabelTextBox label="Nome" v-model="nome"/>
                <TopLabelSelect label="Regiao" :content="regioeSelecao" v-model="regioeSelecionadaSelecao" placeholder="Aperte para Selecionar" />
                <TopLabelTextBox label="CNPJ" v-model="cnpj"/>
                
                <div id="button-container">
                    <Button label="Cadastrar" class="button" @click="cadastrar()"/>
                    <ButtonRed class="button" label="Excluir" @click="deleteE()"/>
                </div>
            </div>
        </div>
    </div>
</template>

<style scoped>
    #button-container{
        margin-top: 30px;
        display: flex;
        flex-direction: column;
        justify-content: space-between;
        gap: 30px;
    }

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

    .button{
        height: 60px;
    }

    #form-container{
        display: flex;
        flex-direction: column;
        height: 60%;
        width: 20%;
        margin-left: 2.5%;
        gap: 10px;
        margin-top: 4%;
        text-align: center;
        font-size: 0.65em;
        justify-content: space-between;
    }
</style>