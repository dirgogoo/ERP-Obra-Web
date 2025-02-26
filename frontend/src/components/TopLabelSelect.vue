<template>
    <div id="textbox_container">
        <h1 id="label">{{ label }}:</h1>
        <select v-model="inputValue" name="selectTop" id="textbox">
            <option v-for="permision in content" :key="permision" :value="permision">{{permision}}</option>
        </select>
    </div>
</template>

<script>
export default {
    name: 'TopLabelSelect',
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
        padding-left: 10px;
    }

    .option{
        width: 100%;
        font-size: 1.5em;
        overflow: hidden;
        text-overflow: ellipsis;
        white-space: nowrap;
    }
    
    #textbox:focus{
        outline-color: #1f93ff;
    }
    

    #label{
        align-self: flex-start;
    }
</style>