<script setup>
import FilterSelector from '@/components/FilterSelector.vue';
import TabelaItemObra from '@/components/TabelaItemObra.vue';
import Button from '@/components/Button.vue';
import { useRouter } from 'vue-router';

import { onBeforeMount, ref, watch } from 'vue';
const props = defineProps({
    obra: {
        type: Object,
    }
});

const etapasSelecao = ref([]);
const etapa = ref();
const etapaObj = ref(0);

const itens = ref([]);

const route = useRouter();

const currentPath = route.currentRoute.value.params.id;



onBeforeMount(() => {
    
    for (let i = 0; i < props.obra.etapa.length; i++) {
        etapasSelecao.value.push(props.obra.etapa[i].etapa.name);
    }
});

const toRouteId = (item) => {
    route.replace("/app/obra/" + currentPath + '/itens/' + item.toLowerCase());

};


watch(etapa, (newVal) => {
    const foundedEtapaObra = props.obra.etapa.find((etapa) => etapa.etapa.name === newVal);
    if (foundedEtapaObra) {
        etapaObj.value = foundedEtapaObra.id;
        const event = new CustomEvent('etapa-updated',{ detail: { etapaId: etapaObj.value } });
        window.dispatchEvent(event);
    }
});

</script>

<template>
    <div id="main-container">
        <div id="form-container">
            <FilterSelector class="FilterSelector" label="Etapa Itens:" :content="etapasSelecao" v-model="etapa"/>
            <FilterSelector class="FilterSelector" label="Ordenar por:" />
            <Button id="button" label="Gerenciar Itens" @click="toRouteId(`gerenciar?etapaObra=${etapaObj}`)" />
        </div>
        <div id="table-container">
            <TabelaItemObra />
        </div>
    </div>
</template>

<style scoped>
#main-container {
    height: 100%;
    width: 100%;
    margin-left: 0.5%;
}

#form-container {
    height: 10%;
    width: 98%;
    display: flex;
    justify-content: center;
    font-size: 0.9em;
    gap: 2%;
}

#button {
    margin-left: 8%;
    font-size: 1.7em;
    ;
    height: 60px;
    width: 60%;
}

.FilterSelector {
    margin-top: 10px;
}

#table-container {
    margin-top: 20px;
    height: 85%;
    width: 98%;
}
</style>