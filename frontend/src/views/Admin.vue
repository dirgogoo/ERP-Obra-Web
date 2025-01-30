<script setup>
     import TopLabelTextBox from '../components/TopLabelTextBox';
     import TopLabelSelect from '../components/TopLabelSelect';
    import Button from '../components/Button';
    import TabelaUsuarios from '../components/TabelaUsuarios.vue';
</script>

<template>
    <div id="main-container">
        <h2>Painel Admin</h2>

        <div id="bottom-container">
            <div id="table-container">
                <TabelaUsuarios/>
            </div>
            <div id="form-container">
                <h1>Cadastro Usuarios</h1>
                <TopLabelTextBox label="Nome"/>
                <TopLabelTextBox label="Senha"/>
                <TopLabelSelect label="Permissão"/>
                <Button label="Cadastrar" class="button"/>
            </div>
        </div>
    </div>
</template>

<style scoped>

    #bottom-container{
        display: flex;
        justify-content: space-around;
        height: 750px;
        margin-top:50px;
        margin-left: 20px;
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
        align-items: center;
        height: 50%;
        width: 20%;
        margin-left: 2.5%;
        margin-top: 4%;
        text-align: center;
        font-size: 0.65em;
        justify-content: space-between;
    }
</style>