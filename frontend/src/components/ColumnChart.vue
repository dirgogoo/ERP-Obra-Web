<template>
    <Bar id="bar" :data="chartData" :options="chartOptions" />
</template>

<script>
import { defineComponent } from 'vue';
import { Bar } from 'vue-chartjs';
import { Chart as ChartJS, Title, Tooltip, Legend, BarElement, CategoryScale, LinearScale } from 'chart.js';

ChartJS.register(CategoryScale, LinearScale, BarElement, Title, Tooltip, Legend);

export default defineComponent({
    name: 'ColumnChart',
    components: {
        Bar
    },
    data() {
        return {
            chartData: {
                labels: ['January', 'February', 'March', 'April', 'May', 'June', 'July'],
                datasets: [
                    {
                        label: 'Dataset 1',
                        backgroundColor: '#42A5F5',
                        data: [40, 20, 12, 28, 39, 50, 33]
                    },
                    {
                        label: 'Dataset 2',
                        backgroundColor: '#66BB6A',
                        data: [30, 25, 15, 35, 45, 55, 40]
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
                    title: {
                        display: true,
                        text: 'Column Chart Example'
                    }
                }
            }
        };
    }
});
</script>

<style scoped>
#bar{
    width: 100%;
}
</style>