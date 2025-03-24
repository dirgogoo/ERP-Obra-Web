<script setup>
import SearchBar from '@/components/Searchbar.vue';
import FilterSelector from '@/components/FilterSelector.vue';
import TabelaItens from '@/components/TabelaItens.vue';
import Button from '@/components/Button.vue';
import TopLabelTextBox from '@/components/TopLabelTextBox.vue';
import ButtonRed from '@/components/ButtonRed.vue';
import TabelaItensEtapa from '@/components/TabelaItensEtapa.vue';
import { useRouter } from 'vue-router';

import { ref, onMounted, watch } from 'vue';
import api from '@/services/axios';

const toSaveItens = ref([]);
const itemSelected = ref([]);
const itemSelectedName = ref("")
const itemQtd = ref();

const selectedId = ref(null);

const search = ref('');

const route = useRouter();

const EtapaObraID = route.currentRoute.value.query.etapaobra

const saveItems = async () => {
    for (const item of toSaveItens.value) {
        try {
            const response = await api.post("/obra/etapa/item", item);
            console.log(`Item ${item.item.nome} saved successfully`);
        } catch (error) {
            console.error(`Error saving item ${item.item.nome}:`, error);
        }
    }
    route.push(`/app/obra/${route.currentRoute.value.params.id}/itens`);
};

watch(itemSelected, (newVal) => {
    itemSelectedName.value = newVal.nome;
});

const changeSelectId = (id) => {
    selectedId.value = id;
}

const addItem = () => {
    if (itemSelected.value || itemQtd.value) {
    toSaveItens.value.push({
        item: itemSelected.value,
        quantidade: itemQtd.value,
        valorTotal : 0,
        etapa : {id : EtapaObraID }
    });
    console.log(toSaveItens.value);
}
};

watch(toSaveItens, (newVal) => {
    console.log(newVal);
});

const deleteE = async () => {
    try { 
        
        const response = await api.delete(`/obra/etapa/item/${selectedId.value}`);
        const event = new CustomEvent('itemetapa-registered');
        window.dispatchEvent(event);
    } catch (error) {
        console.error("Erro ao deletar etapa:", error);
    }
}


</script>

<template>
    <div id="main-container">
        <div id="sides-container">
            <div id="left-container">
                <div id="search-container">
                    <SearchBar v-model="search"/>
                    <FilterSelector label="Tipo:"/>
                </div>
                <div id="table-container">
                    <TabelaItens v-model="itemSelected"  :search="search"/>
                </div>
                <div id="form-container">
                    <div id="input-container">
                        <TopLabelTextBox label="Item" id="input-item" v-model="itemSelectedName"/>
                        <TopLabelTextBox label="Quantidade" id="input-qtd" v-model="itemQtd"/>
                    </div>
                    <div id="button-container">
                        <Button label="Adicionar" class="button" @click="addItem"/>
                        <ButtonRed class="button" label="Excluir" @click="deleteE"/>
                    </div>
                </div>
            </div>
            <div id="right-container">
                <div id="table-container2">
                    <TabelaItensEtapa :etapa-id="EtapaObraID" v-model="toSaveItens"  @update:selectedID="changeSelectId"/>
                </div>
                <div id="button-container2">
                    <Button label="Salvar Mudanças" @click="saveItems" />
                </div>
            </div>
        </div>
    </div>
</template>


<style scoped>

    #main-container{
        height: 100%;
        width: 100%;
    }

    #sides-container{
        display: flex;
        margin-top: 10px;
        justify-content: space-around;
        width: 100%;
        height: 100%;
    }

    #left-container{
        display: flex;
        flex-direction: column;
        align-items: center;
        justify-content: center;
        width: 46%;
        height: 100%;
    }

    #right-container{
        display: flex;
        flex-direction: column;
        align-items: center;
        width: 46%;
        height: 100%;
    }

    #search-container{
        display: flex;
        justify-content: space-around;
        width: 100%;
        height: 10%;
        gap:20px;
    }

    #table-container{
        width: 100%;
        height: 60%;
    }

    #form-container{
        width: 100%;
        height: 30%;
        font-size: 0.68em;
    }

    #input-container{
        display: flex;
        justify-content: space-around;
        width: 100%;
        height: 50%;
    }

    #input-qtd{
        width: 25%;
    }
    #input-item{
        width: 65%;
    }

    #button-container{
        display: flex;
        justify-content: space-around;
        width: 100%;
        height: 23%;
        font-size: 0.8em;
    }

    #table-container2{
        width: 100%;
        height: 85%;
    }

    #button-container2{
        align-self: flex-end;
        width: 40%;
        height: 10%;
        font-size: 0.7em;
    }

    .button{
        width: 30%;
    }
</style>