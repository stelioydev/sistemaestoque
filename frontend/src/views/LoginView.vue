<script setup>
import { ref } from 'vue'; import { useRouter } from 'vue-router'; import { Mail, Lock, Eye, EyeOff, ArrowRight, CheckCircle2 } from 'lucide-vue-next'; import Logo from '../components/Logo.vue'
const email=ref('admin@stockos.com'), password=ref('123456'), show=ref(false), error=ref(''), loading=ref(false); const router=useRouter()
function login(){ error.value=''; const savedUser=JSON.parse(localStorage.getItem('stockos_user')||'null'); const validEmail=(savedUser?.email||'admin@stockos.com').toLowerCase(); const validPassword=localStorage.getItem('stockos_password')||'123456'; if(email.value.trim().toLowerCase()!==validEmail||password.value!==validPassword){error.value='E-mail ou senha inválidos.';return}; loading.value=true; setTimeout(()=>{localStorage.setItem('stockos_auth','true');router.push('/')},350)}
</script>
<template><div class="login-page"><div class="login-glow glow-a"></div><div class="login-glow glow-b"></div><section class="login-card"><Logo/><div class="login-heading"><h1>Bem-vindo de volta</h1><p>Entre com suas credenciais para continuar.</p></div><form @submit.prevent="login">
<label>E-mail</label><div class="input-icon"><Mail :size="20"/><input v-model="email" type="email" autocomplete="username" /></div>
<label>Senha</label><div class="input-icon"><Lock :size="20"/><input v-model="password" :type="show?'text':'password'" autocomplete="current-password"/><button type="button" class="eye" @click="show=!show"><EyeOff v-if="show" :size="20"/><Eye v-else :size="20"/></button></div>
<p v-if="error" class="form-error">{{error}}</p><button class="btn btn-primary login-btn" :disabled="loading">{{loading?'Entrando...':'Entrar no sistema'}}<ArrowRight v-if="!loading" :size="19"/></button>
</form><div class="demo-note"><CheckCircle2 :size="17"/><span>Acesso demonstrativo já preenchido</span></div><div class="credentials"><span><b>E-mail:</b> admin@stockos.com</span><span><b>Senha:</b> 123456</span></div></section></div></template>
