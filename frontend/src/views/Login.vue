<script setup>
import TopLabelTextBox from '../components/TopLabelTextBox';
import Button from '../components/Button';
import api from "../services/axios";
import { ref } from 'vue';
import { useRouter } from 'vue-router';

const username = ref('');
const password = ref('');
const statusMessage = ref('');
const router = useRouter();

const form = ref(false)

const login = async () => {
    console.log(username.value, password.value);

    try {
        const response = await api.post("/login", {
            username: username.value,
            password: password.value
        });
        console.log(response.data);
        console.log(response.headers);
        const token = response.headers["authorization"];
        console.log("Token: ", token);
        localStorage.setItem("token", token); // Salva no localStorage
        localStorage.setItem("username", username.value);
        router.push("/app/home"); // Redireciona para home    
    } catch (error) {
        statusMessage.value = "Credenciais inválidas!";
    }
}
</script>



<template>
    <div id="background">
        <div id="login_container">
            <div id="logo_container">
                <img id="logo" src="../assets/img/logo.png" alt="logo cora duarte" srcset="">
            </div>
            <div id="form_container">



                <v-form v-model="form" h-100>
                    <v-container>
                        <v-row>
                            <v-col cols="12">
                                <v-text-field :rules="[v => !!v || 'Esse campo devera ser preenchido']" class="mb-2" label="Username" clearable v-model="username"></v-text-field>
                            </v-col>
                        </v-row>

                        <v-row>
                            <v-col cols="12">
                                <v-text-field :rules="[v => !!v || 'Esse campo devera ser preenchido']" label="Senha"
                                    placeholder="Escreva sua senha" clearable v-model="password"></v-text-field>
                            </v-col>
                        </v-row>

                        <v-row justify="center">
                            <v-col cols="10">
                            <RouterLink to="/app/home" >
                                <v-btn :disabled="!form" color="#0091EA" size="large" type="submit" variant="elevated"
                                    block @click="login">
                                    Entrar
                                </v-btn>
                            </RouterLink>
                        </v-col>
                        </v-row>



                    </v-container>
                </v-form>

            </div>
        </div>
    </div>
</template>

<style scoped>
* {
    font-size: 10px;
}

#background {
    background: rgb(38, 130, 220);
    background: linear-gradient(145deg, rgba(38, 130, 220, 1) 10%, rgb(10, 51, 73) 100%);
    width: 100vw;
    height: 100vh;
    display: flex;
    align-items: center;
    justify-content: center;

}

#login_container {
    width: 50vh;
    height: 60vh;
    border-radius: 30px;
    background-color: rgb(255, 255, 255);
    box-shadow: rgba(17, 12, 46, 0.4) 10px 10px 50px 25px;
    display: flex;
    align-items: center;
    flex-direction: column;
}

#logo_container {
    margin-top: 40px;
    width: 60%;
    height: auto;
}

#logo {
    width: 100%;
}

#form_container {
    width: 85%;
    height: 65%;
    margin-top: 12%;
}

#Button_Login {
    margin-top: 10%;
    height: 60px;
}
</style>