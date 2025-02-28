<template>
    <v-sheet w-100 h-90  class="d-flex justify-center align-center" color="transparent">
        <v-text-field
            :label="label"
            variant="outlined"
            v-model="inputValue"
            class="w-100"
          ></v-text-field>
    </v-sheet>
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
