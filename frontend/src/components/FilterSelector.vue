<template>
    <div id="main-container">
        <div id="text-holder">
            <h2>{{ label }}</h2>
        </div>
        
        <select name="Filtro">

        </select>
    </div>
</template>

<script>
    export default {
        name: 'Button',
        props: ['label'],
    };
</script>


<style scoped>

    #main-container{
        display: flex;
        justify-content: center;
    }

    select{
        border: 0;
        background-color: #ECECEC;
        width: 100%;
        margin-left:2%;
        height: 35px;
    }

    #text-holder{
        min-width: 160px;
        height: 35px;
    }

</style>