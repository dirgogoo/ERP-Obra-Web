<script setup>
import { RouterLink } from 'vue-router';


</script>

<template>
    <div id="main-container">
        <h2>Cadastro</h2>
        <div id="menu-container">
            <RouterLink class="menu-element" to="/app/cadastro/material">
                <img id="helmet" src="../../assets/img/material.png" alt="Material">
                <h2 class="label-element">Material</h2>
            </RouterLink>
            <RouterLink class="menu-element" to="/app/cadastro/servico">
                <img id="helmet" src="../../assets/img/servico.png" alt="Serviços">
                <h2 class="label-element">Serviço</h2>
            </RouterLink>
            <RouterLink class="menu-element" to="/app/cadastro/cliente" >
                <img id="helmet" src="../../assets/img/cliente.png" alt="Clientes">
                <h2 class="label-element" id="label-cliente">Cliente</h2>
            </RouterLink>
            <RouterLink class="menu-element" to="/app/cadastro/etapa">
                <img id="helmet" src="../../assets/img/etapa.png" alt="Etapas">
                <h2 class="label-element">Etapa</h2>
            </RouterLink>
        </div>
    </div>
</template>

<style scoped> 

    #main-container{
        width: 100%;
        height: 100%;
        
    }

    #menu-container{
        margin-top:7%;
        display: flex;
        justify-content: space-around;
        align-items: center;
        height: 45vh;
        overflow: visible;
    }

    .menu-element{
        color: white;
        background-color: #1988F3;
        height: 100%;
        width: 18%;
        text-align: center;
        border-radius: 30px;
        text-decoration: none;
    }
    .menu-element:hover{
        transform: scale(1.15);
    }

    .label-element{
        margin-top: 10%;
        font-size: 2.5em;
    }

    img{
        height: 150px;
        width: 150px;
        margin-top: 100px;
    }

</style>