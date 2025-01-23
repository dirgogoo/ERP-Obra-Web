
<script setup>

    var lastId = "homeRouter";
   
    setTimeout(() => {
        document.getElementById(lastId).classList.add("menu-selected");
    }, 1000);
    
   function clickMenu(id)
    {
        //console.log("added:",id);
        document.getElementById(id).classList.add("menu-selected");
        if (id != lastId){
            document.getElementById(lastId).classList.remove("menu-selected");
            //console.log("removed:",lastId);
        }
        
        console.log( document.getElementById(id).classList.value)
        lastId = id;
        
        
    } 

</script>

<template >
    <div id="background">
        
        <div id="logo-container">
            <img id="logo" src="../assets/img/logo.png" alt="logo cora duarte" srcset="">
        </div>
        
        <div id="header-container">
            <h1 id="name-display">Olá André</h1>
        </div>
        
        <div id="menu-container">
            <nav>
                <RouterLink @click="clickMenu('homeRouter')"  class="menu-element" id="homeRouter" to="/app/home" >
                    <img id="home" src="../assets/img/Home.png" alt="Home">
                    <h2 class="menu-element-text">Home</h2>
                </RouterLink>
                <RouterLink @click="clickMenu('obraRouter')" class="menu-element" id="obraRouter" to="/app/obra">
                    <img id="helmet" src="../assets/img/Helmet.png" alt="Obras">
                    <h2 class="menu-element-text">Obras</h2>
                </RouterLink>
                <RouterLink  @click="clickMenu('cadastroRouter')" class="menu-element" id="cadastroRouter" to="/app/cadastro">
                    <img id="pencil" src="../assets/img/pencil.png" alt="Cadastro">
                    <h2 class="menu-element-text">Cadastro</h2>
                </RouterLink>
                
            </nav>
                <RouterLink class="menu-element" id="menu-logout" to="/">
                        <img id="door" src="../assets/img/door.png" alt="Logout">
                        <h2 class="menu-element-text">Sair</h2>
                </RouterLink>
                
        </div>
        
        <div id="content-container">
            <router-view />
        </div>

    </div>
</template>





<style scoped>
    #background{
        background-color: #f2f2f2;
        width: 100vw;
        height: 100vh;
    }
    #name-display{
        font-size: 1.5em
    }

    #header-container{
        background-color: white;
        width: 92%;
        height: 8vh;
        margin-left: auto;
        box-shadow: rgba(99, 99, 99, 0.2) 1px 0.1vh 30px 1px;
        border: none;
        display: flex;
        flex-direction:column;
        justify-content: center;
        align-items: flex-end;
        padding-right:30px;
        font-size: 0.9em;
    }

    #logo-container{
        background-color: white;
        height: 8vh;
        width: 30%;
        position: absolute;
        display: flex;
        align-items: center;
    }
    
    #logo{
        height: 7vh;
    }

    #menu-container{
        background-color: #2888E4;
        height: 98vh;
        width: 6%;
        max-width: 100px;
        display: flex;
        float: left;
        flex-direction: column;
        align-items: center;
        box-shadow: rgba(99, 99, 99, 0.2) 0.1vh 0px 30px 1px;
    }
    #menu-container *{
        box-sizing: border-box;
        -webkit-transition: all 0.35s ease;
        transition: all 0.35s ease;
        -webkit-box-sizing: border-box;
    }
    
    nav{
        margin-top: 30px;
        height: 80%;
        display: flex;
        flex-direction: column;
        align-items: center;
        gap:3.5%
    }



    .menu-element{
        display: flex;
        flex-direction:column;
        align-items: center;
        text-decoration: none;
        font-size: 0.6em;
        width: 90%;

        padding: 0 1.2em;
        color: rgba(255, 255, 255, 0.5);
        position: relative;
        text-decoration: none;  
        opacity: 0.8;
    }



    .menu-element:hover{
        color:white;
        opacity: 1;
    }

    .menu-selected{
        color:white;
        border-left: 3px solid #ffffff;
        opacity: 1;
    }

    #menu-logout{
        opacity: 1;
        color:white;
        border-left: none;
    }

    .menu-element-text{
        margin-top: 4px;
    }

    #home{
        height: 60%;
        width: 60%;
    }


    #helmet{
        height: 70%;
        width: 70%;
    }

    #pencil{
        height: 68%;
        width: 60%; 
    }

    #door{
        height: 60%;
        width: 50%;
    }
    
    #content-container{
        position: absolute;
        left: 6%;
        margin-top:1%;
        width: 93%;
        height: 92%;

    }

</style>