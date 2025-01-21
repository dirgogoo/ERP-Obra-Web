<template>
    <div id="textbox_container">
        <h1 id="label">{{ label }}:</h1>
        <select name="selectTop" id="textbox">

        </select>
    </div>
</template>

<script>
    export default {
        name: 'TopLabelTextBox',
        props: ['label'],
    };
</script>

<style scoped>

    #textbox_container{
        width: 95%;
        display: flex;
        flex-direction: column;
        justify-content: center;
        align-items: center;
    }

    #textbox{
        width: 100%;
        height: 40px;
        border-radius: 15px;
        border-style: none;
        background-color: #ECECEC;
        margin-top: 10px;
        font-size: 1.5em;
    }
    
    #textbox:focus{
        outline-color: #1f93ff;
    }
    

    #label{
        align-self: flex-start;
    }
</style>