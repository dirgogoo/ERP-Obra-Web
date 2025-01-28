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
        justify-content: space-between;
    }

    select{
        border: 0;
        background-color: #ECECEC;
        margin-left:2%;
        height: 40px;
        width: 65%;
    }

    #text-holder{
        height: 35px;
    }

</style>