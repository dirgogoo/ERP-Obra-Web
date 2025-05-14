<script setup>
import ButtonRed from '../../components/ButtonRed.vue';
import TopLabelTextBox from '../../components/TopLabelTextBox';
import Button from '../../components/Button';
import TopLabelSelect from '../../components/TopLabelSelect';
import TabelaObraNova from '@/components/TabelaObraNova.vue';
import TopLabelDateBox from '../../components/TopLabelDateBox';
import { onMounted, ref, watch } from 'vue';
import api from '@/services/axios';
import { useRouter } from 'vue-router';


const nome = ref('');
const endereco = ref('');
const codigoupe = ref('');
const centroCusto = ref('');
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
const etapasSelecionadas = ref([]);

const showTopContainer = ref(0);

const regioes = ref([]);
const regioeSelecionadaSelecao = ref();
const regioeSelecao = ref([]);



watch(cliente, (newVal, oldVal) => {
    console.log(newVal, oldVal);
})

const router = useRouter();

const toRouteId = (id) => {
    if (id == 0) {
        router.push("/app/obra/");
    } else {
        router.push("/app/obra/" + id);
    }
};


onMounted(() => {
    api.get('/cliente/all').then(response => {
        clientes.value = response.data;
        for (let i = 0; i < clientes.value.length; i++) {
            clienteSelecao.value.push(clientes.value[i].name);
        }
    });

    api.get('/etapa/all').then(response => {
        etapas.value = response.data;
        for (let i = 0; i < etapas.value.length; i++) {
            etapasSelecao.value.push(etapas.value[i].name);
        }
    });
    api.get('/regiao/all').then(response => {
        regioes.value = response.data;
        for (let i = 0; i < regioes.value.length; i++) {
           regioeSelecao.value.push(regioes.value[i].nome);
        }
    });
});

const getClientId = () => {
    const client = clientes.value.find(client => client.name === cliente.value);
    console.log(client.id);
    return client.id;
}

const getRegiaoId = () => {
    const regiao = regioes.value.find(regiao => regiao.nome === regioeSelecionadaSelecao.value);
    console.log(regiao.id);
    return regiao.id;
}

const parseDate = (dateString) => {
    if (dateString.length !== 10) {
        return 0; // Invalid date format
    }
    // Split the date string into day, month, and year
    // and convert them to numbers
        const [day, month, year] = dateString.split('/').map(Number);
        return new Date(year, month - 1, day);
    };

const cadastrar = async () => {

    if (!nome.value || !endereco.value || !cliente.value || !dataInicio.value || !dataPrevista.value || !codigoupe.value || !centroCusto.value || !regioeSelecionadaSelecao.value ) {
        alert("Preencha todos os campos");
        return;
    }
    if (etapasSelecionadas.value.length === 0) {
        alert("Adicione etapas!");
        return;
    }

    const dateRegex = /^\d{2}\/\d{2}\/\d{4}$/;
    if (!dateRegex.test(dataInicio.value) || !dateRegex.test(dataPrevista.value)) {
        alert("As datas devem estar no formato dd/mm/aaaa!");
        return;
    }

    if (parseDate(dataInicio.value) > parseDate(dataPrevista.value)) {
        alert("Data de início não pode ser maior que a data prevista!");
        return;
    }
    try {
        const response = await api.post("/obra", {
            nome: nome.value,
            dataInicio: dataInicio.value,
            dataPrevista: dataPrevista.value,
            status: 0,
            codigoUPE : codigoupe.value,
            centroCusto: centroCusto.value,
            regiao : {"id" : getRegiaoId()},
            etapa: etapasSelecionadas.value,
            cliente: { id: getClientId() },
            description: endereco.value
        });
        toRouteId(0);
    } catch (error) {
        console.error("Erro ao cadastrar obra:", error);
        toRouteId(0);
    }

}



function addEtapa() {
    if (!etapaSelecionada.value || !etapaValue.value || !etapaPrazo.value || !etapaInicio.value) {
        alert("Preencha todos os campos");
        return;
    }

    if (isNaN(etapaValue.value)) {
        alert("Valor deve ser um número!");
        return;
    }

    const dateRegex = /^\d{2}\/\d{2}\/\d{4}$/;
    if (!dateRegex.test(etapaPrazo.value) || !dateRegex.test(etapaInicio.value)) {
        alert("Data de prazo da Etapa deve estar no formato dd/mm/aaaa!");
        return;
    }

    if(parseDate(etapaInicio.value) > parseDate(etapaPrazo.value)) {
        alert("Data de início da Etapa não pode ser maior que a data prevista!");
        return;
    }

    if(parseDate(etapaInicio.value) < parseDate(dataInicio.value)) {
        alert("Data de início da Etapa não pode ser menor que a data de início da obra!");
        return;
    }

    if(parseDate(etapaPrazo.value) > parseDate(dataPrevista.value)) {
        alert("Data de prazo da Etapa não pode ser maior que a data prevista da obra!");
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
        etapa: { id: selected.id },
        name: selected.name,
        price: etapaValue.value,
        deadline: etapaPrazo.value,
        status: 0,
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
    showTopContainer.value = (showTopContainer.value + 1) % 2;
}
</script>

<template>
    <div id="main-container">
        <h2>Obra/Nova</h2>
        <div id="sides-container">
            <div id="left-container">
                <div id="top-container" v-show="showTopContainer == 0">
                    <h1>Informações Gerais</h1>
                    <div id="form1-container">
                        <TopLabelTextBox label="Nome" v-model="nome" placeholder="Nome" />
                        <TopLabelSelect label="Cliente" :content="clienteSelecao" v-model="cliente" placeholder="Aperte para Selecionar" />
                        
                        <TopLabelTextBox label="Código UPE" v-model="codigoupe" />
                        <TopLabelTextBox label="Centro de custos" v-model="centroCusto" />
                    </div>
                </div>
                <div id="top-container" v-show="showTopContainer == 0">
                    <div id="form1-container">
                        <TopLabelTextBox label="Endereço" v-model="endereco" placeholder="Endereço" />
                        <TopLabelSelect label="Regiao" :content="regioeSelecao" v-model="regioeSelecionadaSelecao"/>
                        <TopLabelDateBox label="Data Inicio" v-model="dataInicio" />
                        <TopLabelDateBox label="Data Prevista" v-model="dataPrevista" />
                    </div>
                </div>
                <div id="bottom-container" v-show="showTopContainer == 1">
                    <h1>Etapas</h1>
                    <div id="form1-container">
                        <div id="textbox-container">
                            <TopLabelSelect label="Nome" :content="etapasSelecao" v-model="etapaSelecionada" />
                            <TopLabelTextBox label="Valor" v-model="etapaValue" />
                            <TopLabelDateBox label="Data Prevista" v-model="etapaPrazo" />
                            <TopLabelDateBox label="Data Inicio" v-model="etapaInicio" />
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
                    <TabelaObraNova v-bind:values="etapasTabela" />
                </div>
                    <div id="button-container">
                        <ButtonRed class="button-form" label="Cancelar" @click="toRouteId(0)"/>
                        <Button class="button-form" label="Criar Nova Obra" @click="cadastrar()" />
                    </div>
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