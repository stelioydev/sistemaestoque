<script setup>
import { ref } from 'vue'
import { useRouter } from 'vue-router'
import { LayoutDashboard, Package, Tags, Truck, ArrowLeftRight, BarChart3, Menu, X, UserRound, LogOut } from 'lucide-vue-next'
import Logo from './Logo.vue'
import { state } from '../data/store'
const router = useRouter(); const mobile = ref(false)
const links = [
  ['/', 'Dashboard', LayoutDashboard], ['/produtos','Produtos',Package], ['/categorias','Categorias',Tags], ['/fornecedores','Fornecedores',Truck], ['/movimentacoes','Movimentações',ArrowLeftRight], ['/relatorios','Relatórios',BarChart3]
]
function logout(){ localStorage.removeItem('stockos_auth'); router.push('/login') }
</script>
<template>
<div class="app-shell">
  <aside class="sidebar" :class="{open:mobile}">
    <div class="sidebar-head"><Logo/><button class="icon-btn mobile-only" @click="mobile=false"><X :size="20"/></button></div>
    <nav class="nav-list">
      <router-link v-for="[to,label,Icon] in links" :key="to" :to="to" @click="mobile=false"><component :is="Icon" :size="19"/><span>{{label}}</span></router-link>
    </nav>
    <div class="sidebar-bottom"><div class="mini-help">StockOS<br><small>Gestão de estoque</small></div></div>
  </aside>
  <div v-if="mobile" class="backdrop" @click="mobile=false"></div>
  <section class="main-area">
    <header class="topbar">
      <button class="icon-btn mobile-only" @click="mobile=true"><Menu :size="22"/></button>
      <div class="topbar-spacer"></div>
      <div class="profile-wrap admin-static">
        <div class="profile-trigger">
          <div class="avatar small" :style="state.user.avatar ? {backgroundImage:`url(${state.user.avatar})`} : {}"><UserRound v-if="!state.user.avatar" :size="20"/></div>
          <div class="profile-copy"><strong>Administrador</strong><small>Acesso total</small></div>
        </div>
        <button class="icon-btn logout-btn" title="Sair do sistema" aria-label="Sair do sistema" @click="logout"><LogOut :size="18"/></button>
      </div>
    </header>
    <main class="content"><slot/></main>
  </section>
</div>
</template>
