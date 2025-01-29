<template>
    <div class="obra-info">
        <h1>Informações da Obra</h1>
        <ul>
            <li><strong>Cliente:</strong> {{ obra.cliente }}</li>
            <li><strong>Código:</strong> {{ obra.codigo }}</li>
            <li><strong>Data de Criação:</strong> {{ obra.dataCriacao }}</li>
            <li><strong>Data de Início:</strong> {{ obra.dataInicio }}</li>
            <li><strong>Data Prevista:</strong> {{ obra.dataPrevista }}</li>
            <li><strong>Data de Término:</strong> {{ obra.dataTermino }}</li>
        </ul>
    </div>
</template>

<script>
export default {
    name: 'ObraInfo',
    data() {
        return {
            obra: {
                cliente: 'Nome do Cliente',
                codigo: '12345',
                dataCriacao: '2023-01-01',
                dataInicio: '2023-02-01',
                dataPrevista: '2023-06-01',
                dataTermino: '2023-05-30'
            }
        };
    }
};
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