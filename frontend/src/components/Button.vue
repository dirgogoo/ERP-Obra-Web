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
    height:95%;
    width:95%;
    border-radius: 15px;
    border-style: none;
    background-color: #1988F3;
    color: white;
    font-size:3em;
}

input:hover{
    background-color: #1567B6;
}
</style>