<script setup>
import SearchBar from '@/components/Searchbar.vue';
import FilterSelector from '@/components/FilterSelector.vue';
import TabelaItens from '@/components/TabelaItens.vue';
import Button from '@/components/Button.vue';
import TopLabelTextBox from '@/components/TopLabelTextBox.vue';
import ButtonRed from '@/components/ButtonRed.vue';
import TabelaItensEtapa from '@/components/TabelaItensEtapa.vue';

</script>

<template>
    <div id="main-container">
        <div id="sides-container">
            <div id="left-container">
                <div id="search-container">
                    <SearchBar/>
                    <FilterSelector label="Tipo:"/>
                </div>
                <div id="table-container">
                    <TabelaItens/>
                </div>
                <div id="form-container">
                    <div id="input-container">
                        <TopLabelTextBox label="Item" id="input-item"/>
                        <TopLabelTextBox label="Quantidade" id="input-qtd"/>
                    </div>
                    <div id="button-container">
                        <Button label="Adicionar" class="button"/>
                    </div>
                </div>
            </div>
            <div id="right-container">
                <div id="table-container2">
                    <TabelaItensEtapa/>
                </div>
                <div id="button-container2">
                    <Button label="Salvar Mudanças"/>
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