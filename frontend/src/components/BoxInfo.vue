<template>
    <div id="main-container">
        <h3>{{label}}:</h3>
        <div id="background" :class="{ red : info < 0, green : info > 0 }" :style="{backgroundColor: color}" >
            <h1>{{info}}</h1>
        </div>
    </div>
</template>

<script>
export default {
    name: 'BoxInfo',
    props: {
        color: {
            type: String,
            required: false
        },
        label: {
            type: String,
            required: true
        },
        info : {
            type: String,
            required: true
        }
    }
}
</script>

<style scoped>
    #main-container{
        height: 100%;
        width: 100%;
        display: flex;
        flex-direction: column;
        gap: 10px;
    }
    #background{
        height: 75%;
        width: 80%;
        border-radius: 20px;
        align-self: center;
        display: flex;
        justify-content: center;
        align-items: center;
        color: white;
    }

    h3{
        font-size: 1.5em;
        margin-right: 10px;
    }

    .red{
        background-color: #CF6567;
    }

    .green{
        background-color: #6DCF65;
    }
</style>