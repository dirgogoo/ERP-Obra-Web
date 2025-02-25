<template>
    <div id="textbox_container">
        <h1 id="label">{{ label }}:</h1>
        <input type="text" autocomplete="off" name="Login" id="textbox" v-model="inputValue">
    </div>
</template>

<script>
export default {
    name: 'TopLabelTextBox',
    props: ['label', 'modelValue'],
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
#textbox_container {
    width: 95%;
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
}

#textbox {
    width: 100%;
    height: 40px;
    border-radius: 15px;
    border-style: none;
    background-color: #ECECEC;
    margin-top: 10px;
    font-size: 1.5em;
}

#textbox:focus {
    outline-color: #1f93ff;
}

#label {
    align-self: flex-start;
}
</style>