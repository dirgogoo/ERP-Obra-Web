<template>
    <div id="main-container">
        <div id="text-holder">
            <h2>{{ label }}</h2>
        </div>
        
        <select name="Filtro" v-model="inputValue">
            <option v-for="permision in content" :key="permision" :value="permision">{{permision}}</option>
        </select>
    </div>
</template>

<script>
    export default {
        name: 'Button',
        props: ['label', 'content','modelValue'],
    emits: ['update:modelValue'],
    computed: {
        inputValue: {
            get() {
                return this.modelValue;
            },
            set(value) {
                this.$emit('update:modelValue', value);
            }
        }
    }
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