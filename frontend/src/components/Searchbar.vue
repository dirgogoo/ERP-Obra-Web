<template>
    <div id="searchbar-container">
        
        <img id="Lupa" src="../assets/img/Lupa.png" alt="Lupa">
        <input type="text" autocomplete="off" name="Pesquisar" id="textbox">
        <img id="Clean" src="../assets/img/X.png" alt="Limpar Pesquisa">
    </div>
</template>

<script>
    export default {
        name: 'Button',
    };
</script>


<style scoped>

    #searchbar-container{
        width: 100%;
       height: 40px;
       border:0;
       background-color: #ECECEC;
        border-radius: 30px;
        display: flex;
        justify-content: space-around;
        align-items: center;
    }

    input{
        background:none;
        border: none;
        width: 75%;
    }

    img{
        height: 55%;
    }

</style>