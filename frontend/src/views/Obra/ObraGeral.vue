<script setup>
import BoxInfo from '@/components/BoxInfo.vue';
import ObraChart from '@/components/ObraChart.vue';

const redColor = '#CF6567';
const blueColor = '#2888E4';
const greenColor = '#6DCF65';
/* 
import { defineProps } from 'vue';
import { ref } from 'vue';




const props = defineProps({
    obra: {
        type: Object,
    }
});
const obra = ref(props.obra);
var data = [0,0,0]
console.log(obra.value);

for (let i = 0; i < obra.value.etapa.length; i++) {
    switch (obra.value.etapa[i].status) {
        case 1:
            data[1] += 1;
            break;
        case 2:
            data[2] += 1;
            break;
        case 3:
            data[3] += 1;
            break;
    }
}
*/
</script>

<template>
    <div id="main-container">

        <div id="left-container">
            <div id="box-grid">
                <div class="box-container">
                    <BoxInfo :color=blueColor label="Data Planejada" info="10/12/2025" />
                </div>
                <div class="box-container">
                    <BoxInfo :color=blueColor label="Gastos Planejados" info="R$100.000" />
                </div>
                <div class="box-container">
                    <BoxInfo :color=blueColor label="Dias Passados" info="166" />

                </div>
                <div class="box-container">
                    <BoxInfo :color=blueColor label="Gastos Realizados" info="R$105.000" />
                </div>
                <div class="box-container">
                    <BoxInfo :color="greenColor" label="Dias Restantes" info="+10" />
                </div>
                <div class="box-container">
                    <BoxInfo :color=redColor label="Saldo" info="-R$5.000" />
                </div>
            </div>
        </div>
        <div id="right-container">
            <h2>Progresso das Etapas:</h2>
            <ObraChart />
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