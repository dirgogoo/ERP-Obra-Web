<template>
    <Pie :data="chartData" :options="chartOptions" />
  </template>
  
  <script>
  import { defineComponent } from 'vue';
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
  
  ChartJS.register(CategoryScale, LinearScale, ArcElement, Title, Tooltip, Legend);
  
  export default defineComponent({
    name: 'ObraChart',
    components: {
      Pie
    },
    data() {
      return {
        chartData: {
          labels: ['Em andamento', 'Atrasado', 'Concluído', 'Não Iniciado'],
          datasets: [
            {
              label: 'Etapas',
              backgroundColor: ['#F2B800', '#F0462E', '#6CCF65', '#2888E4'],
              data: [40, 20, 12, 28]
            }
          ]
        },
        chartOptions: {
          responsive: true,
          plugins: {
            legend: {
                display: true,
                position: 'right',
            },
          }
        }
      };
    }
  });
  </script>