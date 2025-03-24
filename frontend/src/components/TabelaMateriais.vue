<template>
    <div>
        <table>
            <thead>
                <tr>
                    <th id="coluna-id">ID</th>
                    <th>Nome</th>
                    <th id="coluna-unidade">Unidade</th>
                    <th id="coluna-preco">Preço</th>
                </tr>
            </thead>
            <tbody>
                <tr v-for="material in materiais" :key="material.Id" :class="{selected: material.id === selectedId}" @click="selectRow(material.id)">
                    <td>{{ material.id }}</td>
                    <td>{{ material.nome }}</td>
                    <td>{{ material.unidade }}</td>
                    <td>R${{ material.preço }}</td>
                </tr>
            </tbody>
        </table>
        <div id="selectionPage-container">
            <h1 @click="updatePage(currentPage - 1)">&lt;</h1>
            <h1 id="page-label">{{currentPage}}</h1>
            <div @click="updatePage(currentPage + 1)"><h1>&gt;</h1> </div>
        </div>
    </div>
</template>

<script setup>
import { ref, watch, onMounted, onBeforeUnmount } from 'vue';
import api from "../services/axios";

const props = defineProps({
    search: {
        type: String,
    },
    modelValue: {
        type: Number,
        required: true
    },
});

const emit = defineEmits(['update:modelValue']);

watch(() => props.search, () => {
    fetchMateriais(currentPage.value);
});


const selectedId = ref(null);
        const materiais = ref([]);
        const currentPage = ref(1);
        const perPage = ref(16);

        const fetchMateriais = async (page) => {
            try {
                const response = await api.get('/item', {
                    params: {
                        type: 2,
                        page: page - 1,
                        size: perPage.value,
                        sort: 'id,desc',
                        search : props.search
                    }
                });
                
                materiais.value = response.data.content.map(material => ({
                    id: material.id,
                    nome: material.name,
                    unidade:material.unidade,
                    preço :material.valor
                }));

                currentPage.value = page;

            } catch (error) {
                console.error("Erro ao buscar materiais:", error);
            }
        };

        

        const handleUserRegistered = () => {
            fetchMateriais(currentPage.value);
        };

        const selectRow = (id) => {
    selectedId.value = id;
    emit('update:modelValue', id);
};

        onMounted(() => {
            fetchMateriais(currentPage.value);
            window.addEventListener('material-registered', handleUserRegistered);
        });

        onBeforeUnmount(() => {
            window.removeEventListener('material-registered', handleUserRegistered);
        });

        const updatePage = (page) => { {
            if (page > 0){
                fetchMateriais(page);
            }
                
        }};



</script>

<style scoped>
table {
    width: 100%;
    border-collapse: collapse;
}

th, td {
    border: 1px solid #ddd;
    padding: 8px;
}

th {
    background-color: #2888E4;
    color: white;
}

tr{
    background-color: #EDEDED;
}

tr:nth-child(even) {
    background-color: #E3E3E3;
}

#selectionPage-container{
    display: flex;
    margin-top: 10px;
}

#page-label{
    margin: 0 10px;
}

#coluna-id{
    width: 7%;
}

#coluna-unidade{
    width: 8%;
}
#coluna-preco{
    width: 15%;
}

tr:hover {
    background-color: #2889e44f;
}


tr.selected {
    background-color: #2889e477;
}



</style>