<script setup>
    import ButtonRed from '../../components/ButtonRed.vue';
    import TopLabelTextBox from '../../components/TopLabelTextBox';
    import Button from '../../components/Button';
    import TopLabelSelect from '../../components/TopLabelSelect';
    import TabelaObraNova from '@/components/TabelaObraNova.vue';
</script>

<template>
    <div id="main-container">
        <h2>Obra/Nova</h2>
        <div id="sides-container">
            <div id="left-container">
                <div id="top-container">
                    <h1>Informações Gerais</h1>
                    <div id="form1-container">
                        <TopLabelTextBox label="Nome"/>
                        <TopLabelTextBox label="Endereço"/>
                        <TopLabelTextBox label="Cliente"/>
                        <TopLabelTextBox label="Data Inicio"/>
                    </div>
                </div>
                <div id="bottom-container">
                    <h1>Etapas</h1>
                        <div id="form1-container">
                            <div id="textbox-container">
                                <TopLabelSelect label="Nome"/>
                                <TopLabelTextBox label="Valor"/>
                                <TopLabelTextBox label="Prazo"/>
                            </div>
                            <div id="form-button-container">
                                <Button class="button-form" label="Adicionar"/>
                                <ButtonRed class="button-form" label="Remover"/>
                        </div>
                    </div>
                </div>
            </div>
            <div id="right-container">
               <div id="table-container">
                   <TabelaObraNova/>
               </div>

                <div id="button-container">
                    <ButtonRed class="button-form" label="Cancelar"/>
                    <Button class="button-form" label="Criar Nova Obra"/>
                    
                </div>
            </div>

        </div>

    </div>
</template>

<style scoped>
    #main-container{
        height: 89vh;
        
    }

    #sides-container{
        margin-left: 40px;
        display: flex;
        width: 100%;
        height: 100%;
        margin-top: 30px;
        justify-content: space-around;
        
    }

    #left-container{
        display: flex;
        flex-direction: column;
        width: 30%;
        height: 100%;
        gap: 4%;
        font-size: 0.5em;
    }

    #right-container{
        display: flex;
        flex-direction: column;
        width: 60%;
        height: 100%;

    }

    #top-container{
        display: flex;
        flex-direction: column;
        height: 40%;
        width: 90%;

    }

    h1{
        margin-bottom: 20px;
    }

    #form1-container{
        display: flex;
        flex-direction: column;
        width: 100%;
        margin-left: 20px;
        gap:20px;
    }

    #bottom-container{
        display: flex;
        flex-direction: column;
        height: 50%;
    }

    #button-container{
        display: flex;
        margin-top: 20px;
        
    }

    #form-button-container{
        display: flex;
        justify-content: space-between;
        height: 20%;
        width: 90%;
        margin-top: 20px;
    }

    .button-form{
        width: 40%;
    
    }
     #textbox-container{
        display: flex;
        flex-direction: column;
        width: 90%;
        gap: 20px;
    }

    #table-container{
        height: 80%;
        width: 100%;
    }
     #button-container{
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