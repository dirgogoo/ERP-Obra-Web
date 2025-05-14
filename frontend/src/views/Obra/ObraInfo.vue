<template>
    <div class="obra-info">
        <h1>Informações da Obra</h1>
        <ul>
            <li><strong>Cliente:</strong>{{ obra.cliente.name }} </li>
            <li><strong>ID:</strong> {{ obra.id }}</li>
            <li><strong>Codigo UPE:</strong> {{ obra.codigoUPE }}</li>
            <li><strong>Centro de Custos:</strong> {{ obra.centroCusto }}</li>
            <li><strong>Endereço:</strong> {{ obra.description }}</li>
            <li><strong>Regiao:</strong> {{ obra.regiao.nome}}</li>
            <li><strong>Data de Criação:</strong> {{ obra.dataLancamento }}</li>
            <li><strong>Data de Início:</strong> {{ obra.dataInicio }}</li>
            <li><strong>Data Prevista:</strong>{{ obra.dataPrevista }} </li>
            <li><strong>Data de Término:</strong> {{ obra.dataTermino }} </li>
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