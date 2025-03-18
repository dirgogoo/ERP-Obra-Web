<script setup>
    import TabelaEtapaObra from '@/components/TabelaEtapaObra.vue';
    import { defineProps, onMounted } from 'vue';
    import api from '../../services/axios';

    const props = defineProps({
        obra: Object
    });

    const obra = props.obra;

    onMounted(async () => {
        for (let i = 0; i < obra.etapa.length; i++) {
            console.log(obra.etapa[i].etapa.name);

            try {
                const response = await api.get(`/obra/etapa/${obra.etapa[i].id}/saldo`);
                obra.etapa[i].saldo = response.data;
                console.log(response.data);
            } catch (error) {
                console.error("Erro ao buscar itens:", error);
            }
        }
    });


    console.log(obra);
</script>

<template>
    <div id="main-container">
        <div id="table-container">
            <TabelaEtapaObra :etapas="obra.etapa"/>
        </div>
    </div>
</template>

<style scoped>
    #main-container{
        height: 100%;
        width: 100%;
    }

    #table-container{
        margin: auto;
        margin-top: 20px;
        height: 100%;
        width: 98%;
    }

</style>