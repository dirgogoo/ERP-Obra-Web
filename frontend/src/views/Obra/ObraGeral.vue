<script setup>
import BoxInfo from '@/components/BoxInfo.vue';
import ObraChart from '@/components/ObraChart.vue';
import { ref, onMounted } from 'vue';

import { defineProps } from 'vue';
import api from '../../services/axios';

const props = defineProps({
    obra: {
        type: Object,
    }
});

const blueColor = '#2888E4';

const saldoObraV = ref(0);
const obraTotalV = ref(0);

const prazo = ref(props.obra.dataPrevista);
const dataInicio = props.obra.dataInicio;

const dataHoje = new Date();

const getDataAtual = (data1) => {
    console.log(data1)
    const [day1, month1, year1] = data1.split('/');
    const data1N = `${year1}-${month1}-${day1}`;
    const data1Date = new Date(data1N);
    return data1Date;
}

const subtrairDatas = (data1, data2) => {
    console.log(data1, data2)

    // Converte as strings de data para objetos Date
    const data1Date = getDataAtual(data1);
    const data2Date = getDataAtual(data2);

    const diffTime = data1Date - data2Date;
    const diffDays = Math.ceil(diffTime / (1000 * 60 * 60 * 24));

    return diffDays;
}



const diasPassados = ref(0);


const diasRestantes = ref(0);
const diaHoje = dataHoje.toLocaleDateString('pt-BR');


console.log(props.obra);

const dataPie = ref([0, 0, 0, 0])


onMounted(async () => {
    diasPassados.value = Math.max(0, subtrairDatas(diaHoje, dataInicio));

    diasRestantes.value = subtrairDatas(prazo.value, diaHoje);
    var obra = props.obra;
    var saldoObra = 0;
    var obraTotal = 0
    for (let i = 0; i < props.obra.etapa.length; i++) {
        try {
            const response = await api.get(`/obra/etapa/${obra.etapa[i].id}/saldo`);
            saldoObra += response.data;
            obraTotal += obra.etapa[i].price;

            switch (obra.etapa[i].status) {
                case "ANDAMENTO":
                    dataPie.value[0]++;
                    break;
                case "ATRASADO":
                    dataPie.value[1]++;
                    break;
                case "CONCLUIDO":
                    dataPie.value[2]++;
                    break;
                case "NAOINICIADO":
                    dataPie.value[3]++;
                    break;
            }

            console.log(response.data);
        } catch (error) {
            console.error("Erro ao buscar itens:", error);
        }

    }
    console.log(dataPie.value);
    saldoObraV.value = saldoObra;
    obraTotalV.value = obraTotal;
});



</script>

<template>
    <div id="main-container">

        <div id="left-container">
            <div id="box-grid">
                <div class="box-container">
                    <BoxInfo :color=blueColor label="Data Planejada" :info="prazo" />
                </div>
                <div class="box-container">
                    <BoxInfo :color=blueColor label="Gastos Planejados" :info="obraTotalV" />
                </div>
                <div class="box-container">
                    <BoxInfo :color=blueColor label="Dias Passados" :info="diasPassados" />

                </div>
                <div class="box-container">
                    <BoxInfo :color=blueColor label="Gastos Realizados" :info="obraTotalV - saldoObraV" />
                </div>
                <div class="box-container">
                    <BoxInfo label="Dias Restantes" :info="diasRestantes" />
                </div>
                <div class="box-container">
                    <BoxInfo label="Saldo" :info="saldoObraV" />
                </div>
            </div>
        </div>
        <div id="right-container">
            <h2>Progresso das Etapas:</h2>
            <ObraChart :dataPie="dataPie" />
        </div>
    </div>

</template>

<style scoped>
#main-container {
    display: flex;
    height: 100%;
    width: 100%;
}

#left-container {
    display: flex;
    width: 65%;
    height: 100%;
    justify-content: center;
    align-items: center;
}

#right-container {
    width: 35%;
    height: 100%;
}

#box-grid {
    height: 80%;
    width: 85%;
    display: grid;
    grid-template-columns: 1fr 1fr;
    grid-template-rows: repeat(3, 1fr);
    gap: 10%;
}

.box-container {
    width: 23vw;
    height: 13vh;
}

#right-container {
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
    margin-top: 20px;
}

h2 {
    align-self: flex-start;
}
</style>