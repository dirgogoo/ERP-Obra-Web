<template>
    <div id="searchbar-container">
        <img id="Lupa" src="../assets/img/Lupa.png" alt="Lupa">
        <input type="text" autocomplete="off" name="Pesquisar" id="textbox" placeholder="Pesquisar" v-model="inputValue" @keyup.enter="updateModelValue">
        <img id="Clean" src="../assets/img/X.png" alt="Limpar Pesquisa" @click="clearSearch">
    </div>
</template>

<script>
export default {
    name: 'Searchbar',
    props: ['modelValue'],
    emits: ['update:modelValue'],
    data() {
        return {
            inputValue: this.modelValue
        };
    },
    methods: {
        updateModelValue() {
            this.$emit('update:modelValue', this.inputValue);
        },
        clearSearch() {
            this.inputValue = '';
            this.$emit('update:modelValue', '');
        }
    },
    watch: {
        modelValue(newValue) {
            this.inputValue = newValue;
        }
    }
}
</script>

<style scoped>
#searchbar-container {
    width: 100%;
    height: 35px;
    border: 0;
    background-color: #ECECEC;
    border-radius: 30px;
    display: flex;
    justify-content: space-around;
    align-items: center;
}

input {
    background: none;
    border: none;
    width: 75%;
    height: 40px;
    outline: none;
    font-size: 1.2em;
}

img {
    height: 55%;
}
</style>