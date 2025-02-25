<script setup>
     import TopLabelTextBox from '../components/TopLabelTextBox';
     import TopLabelSelect from '../components/TopLabelSelect';
    import Button from '../components/Button';
    import TabelaUsuarios from '../components/TabelaUsuarios.vue';
    import { ref } from 'vue';
    import api from "../services/axios";

    const permisions = ["SUPERADMIN","ADMIN", "USER"];

    const username = ref('');
    const password = ref('');
    const permission = ref('');

    const cadastrar = async () => {
        console.log(username, password, permission);
        const profiles = [1]
        if (permission.value === "SUPERADMIN"){
            profiles.push(2);
            profiles.push(3);
        } else if (permission.value === "ADMIN"){
            profiles.push(2);
        }
        try{
            const response = await api.post("/user", {
                username: username.value,
                password: password.value,
                profiles: profiles
            });
            console.log(response.data);
            const event = new CustomEvent('user-registered');
            window.dispatchEvent(event);
        } catch (error) {
            console.error("Erro ao cadastrar usuário:", error);
        }
    }

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
                <TopLabelTextBox label="Nome" v-model="username"/>
                <TopLabelTextBox label="Senha" v-model="password"/>
                <TopLabelSelect label="Permissão" :content="permisions" v-model="permission"/>
                <Button label="Cadastrar" class="button" @click="cadastrar"/>
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