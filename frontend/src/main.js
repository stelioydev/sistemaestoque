import { createApp } from 'vue'
import { createRouter, createWebHistory } from 'vue-router'
import App from './App.vue'
import LoginView from './views/LoginView.vue'
import DashboardView from './views/DashboardView.vue'
import ProductsView from './views/ProductsView.vue'
import CategoriesView from './views/CategoriesView.vue'
import SuppliersView from './views/SuppliersView.vue'
import MovementsView from './views/MovementsView.vue'
import ReportsView from './views/ReportsView.vue'
import './style.css'

const routes = [
  { path: '/login', name: 'login', component: LoginView, meta: { guest: true } },
  { path: '/', name: 'dashboard', component: DashboardView },
  { path: '/produtos', name: 'products', component: ProductsView },
  { path: '/categorias', name: 'categories', component: CategoriesView },
  { path: '/fornecedores', name: 'suppliers', component: SuppliersView },
  { path: '/movimentacoes', name: 'movements', component: MovementsView },
  { path: '/relatorios', name: 'reports', component: ReportsView },
  { path: '/:pathMatch(.*)*', redirect: '/' }
]

const router = createRouter({ history: createWebHistory(), routes })
router.beforeEach((to) => {
  const logged = localStorage.getItem('stockos_auth') === 'true'
  if (!to.meta.guest && !logged) return '/login'
  if (to.meta.guest && logged) return '/'
})

createApp(App).use(router).mount('#app')
