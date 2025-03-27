<script setup>
import ButtonRed from '../../components/ButtonRed.vue';
import TopLabelTextBox from '../../components/TopLabelTextBox';
import Button from '../../components/Button';
import TopLabelSelect from '../../components/TopLabelSelect';
import TabelaObraNova from '@/components/TabelaObraNova.vue';
import { onMounted, ref } from 'vue';
import api from '@/services/axios';


const nome = ref('');
const endereco = ref('');
const cliente = ref('');
const dataInicio = ref('');
const dataPrevista = ref('');

const etapas = ref([]);
const etapaSelecionada = ref([]);
const etapasSelecao = ref([]);
const etapaValue = ref();
const etapaPrazo = ref('');
const etapaInicio = ref('');
const etapasTabela = ref([]);
const etapasSelecionadas = ref([]);

const showTopContainer = ref(true);



api.get('/etapa/all').then(response => {
    etapas.value = response.data;
    for (let i = 0; i < etapas.value.length; i++) {
        etapasSelecao.value.push(etapas.value[i].name);
    }
});

const cadastrar = async () => {

        try{
            const response = await api.post("/obra", {
                nome: nome.value,
                dataInicio: dataInicio.value,
                dataPrevista: dataPrevista.value,
                status : 0,
                etapa : etapasSelecionadas.value,
            });
        } catch (error) {
            console.error("Erro ao cadastrar obra:", error);
        }
    }


    function addEtapa() {
    if (!etapaSelecionada.value || !etapaValue.value || !etapaPrazo.value) {
        alert("Preencha todos os campos");
        return;
    }
    
    const founded = etapasTabela.value.find((etapa) => etapa.etapa === etapaSelecionada.value);
    if (founded) {
        etapasTabela.value.splice(etapasTabela.value.indexOf(founded), 1);
        etapasSelecionadas.value.splice(etapasSelecionadas.value.indexOf(etapasSelecionadas.value.find((etapa) => etapa.name === etapaSelecionada.value)), 1);
    }
    const selected = etapas.value.find(etapa => etapa.name === etapaSelecionada.value);

    etapasTabela.value.push({
        id: selected.id,
        etapa: selected.name,
        valor: etapaValue.value,
        prazo: etapaPrazo.value,
        inicio: etapaInicio.value
    });

    etapasSelecionadas.value.push({
        id: selected.id,
        etapa : { id: selected.id},
        name: selected.name,
        price: etapaValue.value,
        deadline: etapaPrazo.value, 
        status:0,
        dataInicio: etapaInicio.value
    });
    console.log(etapasSelecionadas.value);
}

function removeEtapa() {
    if (!etapaSelecionada.value) {
        alert("Selecione a etapa!");
        return;
    }

    const founded = etapasTabela.value.find((etapa) => etapa.etapa === etapaSelecionada.value);
    if (founded) {
        etapasTabela.value.splice(etapasTabela.value.indexOf(founded), 1);
        etapasSelecionadas.value.splice(etapasSelecionadas.value.indexOf(etapasSelecionadas.value.find((etapa) => etapa.name === etapaSelecionada.value)), 1);
    }
    console.log(etapasSelecionadas.value);
}

function toggleContainers() {
    showTopContainer.value = !showTopContainer.value;
}
</script>

<template>
    <div id="main-container">
        <h2>Obra/Editar</h2>
        <div id="sides-container">
            <div id="left-container">
                <div id="top-container" v-show="showTopContainer">
                    <h1>Informações Gerais</h1>
                    <div id="form1-container">
                        <TopLabelTextBox label="Nome" v-model="nome" />
                        <TopLabelTextBox label="Endereço" v-model="endereco" />
                        <TopLabelTextBox label="Cliente" v-model="cliente" />
                        <TopLabelTextBox label="Data Inicio" v-model="dataInicio" />
                        <TopLabelTextBox label="Data Prevista" v-model="dataPrevista" />
                    </div>
                </div>
                <div id="bottom-container" v-show="!showTopContainer">
                    <h1>Etapas</h1>
                    <div id="form1-container">
                        <div id="textbox-container">
                            <TopLabelSelect label="Nome" :content="etapasSelecao" v-model="etapaSelecionada" />
                            <TopLabelTextBox label="Valor" v-model="etapaValue"/>
                            <TopLabelTextBox label="Data Prevista" v-model="etapaPrazo"/>
                            <TopLabelTextBox label="Data Inicio" v-model="etapaInicio" />
                        </div>
                        <div id="form-button-container">
                            <Button class="button-form" label="Adicionar" @click="addEtapa()" />
                            <ButtonRed class="button-form" label="Remover" @click="removeEtapa()" />
                        </div>
                    </div>
                </div>
                <div id="page-selector">
                    <h1 id="page-selector-options"><a href="javascript:void(0)" @click="toggleContainers">&lt;</a> <a href="javascript:void(0)" @click="toggleContainers">&gt;</a></h1>

                </div>
            </div>
            <div id="right-container">
                <div id="table-container">
                    <TabelaObraNova v-bind:values="etapasTabela" />
                </div>
                <RouterLink to="/app/obra" id="RouterLink">
                <div id="button-container">
                    <ButtonRed class="button-form" label="Voltar" />
                    <Button class="button-form" label="Editar Obra" @click="cadastrar()"/>

                </div>
            </RouterLink>
            </div>

        </div>

    </div>
</template>

<style scoped>
#main-container {
    height: 89vh;

}

#page-selector-options{
    display: flex;
    justify-content: space-between;
    width: 100%;
    font-size: 6em;
}
a {
    text-decoration: none;
    color: black;
}

#sides-container {
    margin-left: 40px;
    display: flex;
    width: 100%;
    height: 100%;
    margin-top: 30px;
    justify-content: space-around;

}

#RouterLink {
    text-decoration: none;
}

#left-container {
    display: flex;
    flex-direction: column;
    width: 30%;
    height: 100%;
    gap: 4%;
    font-size: 0.5em;
}

#right-container {
    display: flex;
    flex-direction: column;
    width: 60%;
    height: 100%;

}

#top-container {
    display: flex;
    flex-direction: column;
    width: 90%;

}

h1 {
    margin-bottom: 20px;
}

#form1-container {
    display: flex;
    flex-direction: column;
    flex: space-between;
    width: 100%;
    margin-left: 20px;
    gap: 20px;
}

#bottom-container {
    display: flex;
    flex-direction: column;

}

#button-container {
    display: flex;
    margin-top: 20px;
    height: 60px;
}

#form-button-container {
    display: flex;
    justify-content: space-between;
    height: 20%;
    width: 90%;
    margin-top: 20px;
}

.button-form {
    width: 40%;

}

#textbox-container {
    display: flex;
    flex-direction: column;
    justify-content: space-between;
    width: 90%;
    gap: 20px;
}

#table-container {
    height: 80%;
    width: 100%;
}

#button-container {
    display: flex;
    justify-content: flex-end;
    margin-top: 30px;
    margin-right: 20px;
    width: 80%;
    gap: 50px;
    margin-left: auto;
    font-size: 0.7em;
}
</style>