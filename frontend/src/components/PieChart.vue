<template>
    <Pie id="pie" :data="chartData" :options="chartOptions" />
</template>

<script>
import { defineComponent } from 'vue';
import { Pie } from 'vue-chartjs';
import { Chart as ChartJS, Title, Tooltip, Legend, ArcElement, CategoryScale, LinearScale } from 'chart.js';

ChartJS.register(CategoryScale, LinearScale, ArcElement, Title, Tooltip, Legend);

export default defineComponent({
    name: 'PieChart',
    components: {
        Pie
    },
    data() {
        return {
            chartData: {
                labels: ['Cliente A', 'Cliente B', 'Cliente C', 'Cliente D'],
                datasets: [
                    {
                        label: 'Lucro',
                        backgroundColor: ['#F2B800', '#F0462E', '#6CCF65', '#2888E4'],
                        data: [5000, 3000, 4000, 2000]
                    }
                ]
            },
            chartOptions: {
                responsive: true,
                plugins: {
                    legend: {
                        display: true,
                        position: 'right'
                    }
                }
            }
        };
    }
});
</script>

<style scoped>
    #pie{
        width: 100%;
        height: 100%;
    }
</style>