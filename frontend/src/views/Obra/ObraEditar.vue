<script setup>
import ButtonRed from '../../components/ButtonRed.vue';
import TopLabelTextBox from '../../components/TopLabelTextBox';
import Button from '../../components/Button';
import TopLabelSelect from '../../components/TopLabelSelect';
import {  onMounted, ref, warn } from 'vue';
import api from '@/services/axios';
import { useRouter } from 'vue-router';
import TabelaObraEdit from '@/components/TabelaObraEdit.vue';

const route = useRouter();


const nome = ref('');
const endereco = ref('');
const cliente = ref('');
const dataInicio = ref('');
const dataPrevista = ref('');


const clientes = ref([]);
const clienteSelecao = ref([]);

const etapas = ref([]);
const etapaSelecionada = ref([]);
const etapasSelecao = ref([]);
const etapaValue = ref();
const etapaPrazo = ref('');
const etapaInicio = ref('');
const etapasTabela = ref([]);
const etapasRemover = []

const showTopContainer = ref(true);

const currentPath = route.currentRoute.value.params.id;



api.get('/etapa/all').then(response => {
    etapas.value = response.data;
    for (let i = 0; i < etapas.value.length; i++) {
        etapasSelecao.value.push(etapas.value[i].name);
    }
});

api.get('/cliente/all').then(response => {
    clientes.value = response.data;
    for (let i = 0; i < clientes.value.length; i++) {
        clienteSelecao.value.push(clientes.value[i].name);
    }
});


const getClientId = () => {
    const client = clientes.value.find(client => client.name === cliente.value);
    console.log(client.id);
    return client.id;
}

onMounted(async () => {
    try {
        const response = api.get("/obra/" + currentPath);
        const obra = (await response).data;
        nome.value = obra.nome;
        cliente.value = obra.cliente.name;
        dataInicio.value = obra.dataInicio;
        endereco.value = obra.description;
        dataPrevista.value = obra.dataPrevista;
        etapasTabela.value = obra.etapa
        console.log(obra, "obra loaded");
    } catch (error) {
        console.error("Error fetching obra data:", error);
    }
});

const cadastrar = async () => {

    try {
        for (const etapaId of etapasRemover) {
            await api.delete(`/obra/etapa/${etapaId}`);
        }
        etapasRemover.length = 0; // Clear the array after deletion
    } catch (error) {
        console.error("Erro ao deletar etapas:", error);
    }

    try {
        const response = await api.put("/obra", {
            id: currentPath,
            nome: nome.value,
            dataInicio: dataInicio.value,
            dataPrevista: dataPrevista.value,
            status: 0,
            cliente: { id: getClientId() },
            description: endereco.value,
            etapa: etapasTabela.value,
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

    var founded = etapasTabela.value.find((etapa) => etapa.etapa.name === etapaSelecionada.value);
    if (founded) {
        etapasTabela.value.splice(etapasTabela.value.indexOf(founded), 1);
    
    }else{
        
    }
    const selected = etapas.value.find(etapa => etapa.name === etapaSelecionada.value);

    etapasTabela.value.push({
        id: founded ? founded.id : 0,
        etapa: { id: selected.id, name : selected.name },
        price: etapaValue.value,
        deadline: etapaPrazo.value,
        status: 0,
        dataInicio: etapaInicio.value
    });
    console.log(etapasTabela.value);
}

function removeEtapa() {
    if (!etapaSelecionada.value) {
        alert("Selecione a etapa!");
        return;
    }

    const founded = etapasTabela.value.find((etapa) => etapa.etapa.name === etapaSelecionada.value);
    if (founded) {
        etapasRemover.push(founded.id)
        etapasTabela.value.splice(etapasTabela.value.indexOf(founded), 1);
    }
    console.log(etapasTabela.value, etapaSelecionada.value);
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
                        <TopLabelSelect label="Cliente" :content="clienteSelecao" v-model="cliente" />
                        <TopLabelTextBox label="Data Inicio" v-model="dataInicio" />
                        <TopLabelTextBox label="Data Prevista" v-model="dataPrevista" />
                    </div>
                </div>
                <div id="bottom-container" v-show="!showTopContainer">
                    <h1>Etapas</h1>
                    <div id="form1-container">
                        <div id="textbox-container">
                            <TopLabelSelect label="Nome" :content="etapasSelecao" v-model="etapaSelecionada" />
                            <TopLabelTextBox label="Valor" v-model="etapaValue" />
                            <TopLabelTextBox label="Data Prevista" v-model="etapaPrazo" />
                            <TopLabelTextBox label="Data Inicio" v-model="etapaInicio" />
                        </div>
                        <div id="form-button-container">
                            <Button class="button-form" label="Adicionar" @click="addEtapa()" />
                            <ButtonRed class="button-form" label="Remover" @click="removeEtapa()" />
                        </div>
                    </div>
                </div>
                <div id="page-selector">
                    <h1 id="page-selector-options"><a href="javascript:void(0)" @click="toggleContainers">&lt;</a> <a
                            href="javascript:void(0)" @click="toggleContainers">&gt;</a></h1>

                </div>
            </div>
            <div id="right-container">
                <div id="table-container">
                    <TabelaObraEdit :values="etapasTabela" />
                </div>
                <RouterLink to="/app/obra" id="RouterLink">
                    <div id="button-container">
                        <ButtonRed class="button-form" label="Voltar" />
                        <Button class="button-form" label="Editar Obra" @click="cadastrar()" />

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

#page-selector-options {
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