import { createRouter, createWebHistory } from 'vue-router'

import PublicLayout from '../layouts/PublicLayout.vue';
import PrivateLayout from '../layouts/PrivateLayout.vue';
import Login from '../views/Login.vue';
import Home from '@/views/Home.vue';
import Cadastro from '@/views/Cadastro.vue';

const isAuthenticated = () => {
  // A fazer autenticação
  return true;
};

const routes = [
  {
    path: '/',
    component: PublicLayout,
    beforeEnter: (to, from, next) => {
      if (isAuthenticated()) {
        next('/app');
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
      { path: '/app/cadastro', name: 'Cadastro', component:Cadastro },
    ],
  },
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router
