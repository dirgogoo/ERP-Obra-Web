<template>
    <div class="obra-info">
        <h1>Informações da Obra</h1>
        <ul>
            <li><strong>Cliente:</strong> </li>
            <li><strong>Código:</strong> {{ obra.id }}</li>
            <li><strong>Data de Criação:</strong> {{ obra.dataLancamento }}</li>
            <li><strong>Data de Início:</strong> {{ obra.dataInicio }}</li>
            <li><strong>Data Prevista:</strong> </li>
            <li><strong>Data de Término:</strong> </li>
        </ul>
    </div>
</template>

<script setup>
import { ref} from 'vue';
const props = defineProps({
    obra: {
      type: Object,
    }
  });
const obra = ref(props.obra);
</script>

<style scoped>
.obra-info {
    font-family: Arial, sans-serif;
}

.obra-info h1 {
    font-size: 24px;
    margin-bottom: 20px;
}

.obra-info ul {
    list-style-type: none;
    padding: 0;
}

.obra-info li {
    margin-bottom: 10px;
}
</style>