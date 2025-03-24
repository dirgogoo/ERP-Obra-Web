<template>
  <Pie v-bind:data="chartData" v-bind:options="chartOptions" />
  </template>

  <script setup>
  import { defineProps, onMounted, ref, watch} from 'vue';
  import { Pie } from 'vue-chartjs';
  import {
    Chart as ChartJS,
    Title,
    Tooltip,
    Legend,
    ArcElement,
    CategoryScale,
    LinearScale
  } from 'chart.js';
  
  
  const isLoading = ref(true);
  const props = defineProps({
    dataPie: {
      type: Array,
      required: true
    }
  });
  
  console.log(props.data);


  const chartData = ref({
    labels: ['Em andamento', 'Atrasado', 'Concluído', 'Não Iniciado'],
    datasets: [
      {
        label: 'Etapas',
        backgroundColor: ['#F2B800', '#F0462E', '#6CCF65', '#2888E4'],
        data: [props.dataPie[0], props.dataPie[1], props.dataPie[2], props.dataPie[3]]
      }
    ]
  });
  
  const chartOptions = ref({
    responsive: true,
    plugins: {
      legend: {
        display: true,
        position: 'right',
      },
    }
  });
    ChartJS.register(CategoryScale, LinearScale, ArcElement, Title, Tooltip, Legend);
    console.log('ChartJS', ChartJS);


  </script>