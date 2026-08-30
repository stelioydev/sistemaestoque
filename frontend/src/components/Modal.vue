<script setup>import { X } from 'lucide-vue-next'; defineProps({title:String}); defineEmits(['close'])</script>
<template><div class="modal-backdrop" @mousedown.self="$emit('close')"><div class="modal"><div class="modal-head"><h2>{{title}}</h2><button class="icon-btn" @click="$emit('close')"><X :size="20"/></button></div><div class="modal-body"><slot/></div></div></div></template>
