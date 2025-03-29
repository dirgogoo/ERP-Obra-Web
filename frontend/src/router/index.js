import { createRouter, createWebHistory } from 'vue-router'

import PublicLayout from '../layouts/PublicLayout.vue';
import PrivateLayout from '../layouts/PrivateLayout.vue';
import Login from '../views/Login.vue';
import Home from '@/views/Home.vue';
import Cadastro from '@/views/Cadastro/Cadastro.vue';
import ClienteCadastro from '@/views/Cadastro/ClienteCadastro.vue';
import EtapaCadastro from '@/views/Cadastro/EtapaCadastro.vue';
import MaterialCadastro from '@/views/Cadastro/MaterialCadastro.vue';
import ServicoCadastro from '@/views/Cadastro/ServicoCadastro.vue';
import Obra from '@/views/Obra/Obra.vue';
import ObraCriar from '@/views/Obra/ObraCriar.vue';
import ObraId from '@/views/Obra/ObraId.vue';
import ObraGeral from '@/views/Obra/ObraGeral.vue';
import ObraEtapas from '@/views/Obra/ObraEtapas.vue';
import ObraItens from '@/views/Obra/ObraItens.vue';
import ObraItensGerenciar from '@/views/Obra/ObraItensGerenciar.vue';
import ObraInfo from '@/views/Obra/ObraInfo.vue';
import Admin from '@/views/Admin.vue';
import ObraEditar from '@/views/Obra/ObraEditar.vue';

const isAuthenticated = () => {
  const token = localStorage.getItem("token");
  return token !== null;
};

const routes = [
  {
    path: '/',
    component: PublicLayout,
    beforeEnter: (to, from, next) => {
      if (isAuthenticated()) {
        next('/app/home');
      } else {
        next();
      }
    },
    children: [
      { path: '/', name: 'Login', component: Login},
    ],
  },
  {
    path: '/app',
    component: PrivateLayout,
    beforeEnter: (to, from, next) => {
      if (isAuthenticated()) {
        next();
      } else {
        next('/');
      }
    },
    children: [
      { path: '/app/home', name: 'Home', component:Home },
      { path: '/app/obra', name: 'Obra', component:Obra},
      { path: '/app/obra/editar/:id', name: 'ObraEditar', component:ObraEditar},
      { path: '/app/obra/:id', name: 'ObraId', component:ObraId, children: [
        { path: '/app/obra/:id/geral', name: 'ObraGeral', component:ObraGeral},
        { path: '/app/obra/:id/etapas', name: 'ObraEtapas', component:ObraEtapas},
        { path: '/app/obra/:id/itens', name: 'ObraItens', component:ObraItens},
        { path: '/app/obra/:id/itens/gerenciar', name: 'ObraItensGerenciar', component:ObraItensGerenciar},
        { path: '/app/obra/:id/info', name: 'ObraInfo', component:ObraInfo},
      ]},
      { path: '/app/obra/criar', name: 'ObraCriar', component:ObraCriar},
      { path: '/app/cadastro', name: 'Cadastro', component:Cadastro},
      { path: '/app/cadastro/cliente', name: 'Cliente', component:ClienteCadastro},
      { path: '/app/cadastro/etapa', name: 'Etapa', component:EtapaCadastro},
      { path: '/app/cadastro/material', name: 'Material', component:MaterialCadastro},
      { path: '/app/cadastro/servico', name: 'Servico', component:ServicoCadastro},
      { path: '/app/admin', name: 'Admin', component:Admin},
    ],
  },
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router
