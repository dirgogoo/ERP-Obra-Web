<template>
    <Line id="line" :data="chartData" :options="chartOptions" />
</template>

<script>
import { defineComponent } from 'vue';
import { Line } from 'vue-chartjs';
import {
    Chart as ChartJS,
    Title,
    Tooltip,
    Legend,
    LineElement,
    PointElement,
    CategoryScale,
    LinearScale
} from 'chart.js';

ChartJS.register(CategoryScale, LinearScale, LineElement, PointElement, Title, Tooltip, Legend);

export default defineComponent({
    name: 'LineChart',
    components: {
        Line
    },
    data() {
        return {
            chartData: {
                labels: ['January', 'February', 'March', 'April', 'May', 'June', 'July'],
                datasets: [
                    {
                        label: 'Dataset 1',
                        backgroundColor: '#42A5F5',
                        borderColor: '#42A5F5',
                        data: [40, 20, 12, 28, 30, 22, 44],
                        fill: false
                    },
                    {
                        label: 'Dataset 2',
                        backgroundColor: '#FFA726',
                        borderColor: '#FFA726',
                        data: [30, 25, 15, 35, 40, 30, 50],
                        fill: false
                    }
                ]
            },
            chartOptions: {
                responsive: true,
                plugins: {
                    legend: {
                        display: true,
                        position: 'top',
                    },
                }
            }
        };
    }
});
</script>

<style scoped>
    #line{
        width:100%;
    }
</style>