<template>
    <input type="button" :value=label>
</template>

<script>
    export default {
        name: 'Button',
        props: ['label'],
    };
</script>


<style scoped>

input{
    height:60px;
    border-radius: 15px;
    border-style: none;
    background-color: #F3191D;
    color: white;
    font-size:3em;
}

input:hover{
    background-color: #C6191C;
}
</style>