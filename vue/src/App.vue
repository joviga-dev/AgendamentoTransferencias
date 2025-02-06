<script setup lang="ts">
import {  RouterView } from 'vue-router'
import MenuSuperior from '@/components/MenuSuperior.vue'
import Toast from 'primevue/toast'
</script>

<template>
  <header>
    <menu-superior></menu-superior>
  </header>
  <RouterView />

  <Toast />
</template>

<style scoped>
header {
  position: fixed;
  top: 0;
  left: 0;
  width: 100%;
  background: white;
  z-index: 1000;
  box-shadow: 0px 2px 5px rgba(0, 0, 0, 0.1);
}

main {
  margin-top: 60px;
  padding: 20px;
}
</style>
