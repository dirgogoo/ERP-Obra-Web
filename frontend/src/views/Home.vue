<script setup>
    import LineChart from '@/components/LineChart.vue';
    import ColumnChart from '@/components/ColumnChart.vue';
    import PieChart from '@/components/PieChart.vue';
    import { ref } from 'vue';

    const lineChartData = ref([
        // Add your line chart data here
    ]);

    const columnChartData = ref([
        // Add your column chart data here
    ]);

    const rankingChartData = ref([
        // Add your ranking chart data here
    ]);

    const pieChartData = ref([
        // Add your pie chart data here
    ]);
</script>

<template>
    <div id="main-container">
        <h2>Home</h2>
        <div id="main-grid">
            <div id="top-grid" class="subgrid-container">  

                <div class="info-container">
                    Obra concluidas recentemente
                </div>

                <div class="info-container">
                    Obra atrasadas recentemente
                </div>

                <div id="percent-grid">
                    <div class="info-container percent-container">
                        Porcentagem Lucro recentemente
                    </div>
                    <div class="info-container percent-container">
                        Porcentagem Obras recentemente
                    </div>
                </div>

                <div class="info-container">
                    <LineChart />
                </div>

            </div>
            <div id="bottom-grid" class="subgrid-container">
                <div class="info-container">
                    <ColumnChart/>
                </div>

                <div class="info-container">
                    Raking Cliente por Numero de Obra
                </div>
                <div class="info-container">
                    <PieChart />
                </div>
            </div>
        </div>
    </div>
</template>

<style scoped>
    #main-grid{
        align-self: center;
        display: flex;
        flex-direction: column;
        margin-top: 5vh;
        width: 100%;
        height: 75vh;
        justify-content: space-around;
        align-items: center;
    }

    .info-container{
        background-color: rgb(230, 230, 230);
        border-radius: 20px;
        text-align: center;
        display: flex;
        align-items: center;
        justify-content: center;
        padding: 10px;
        
    }

    .subgrid-container{
        width: 95%;
        height: 45%;
    }

    #top-grid{
        display:grid;
        grid-template-columns: 0.3fr 0.3fr 0.3fr 0.5fr;
        grid-template-rows: 1fr;
        gap:25px;
    }

    #bottom-grid{
        display:grid;
        grid-template-columns: 0.5fr 0.5fr 0.3fr;
        grid-template-rows: 1fr;
        gap:25px;
    }

    #percent-grid{
        display: flex;
        flex-direction: column;
        justify-content: space-between;
    }

    .percent-container{
        width:96%;
        height: 40%;
    }

</style>