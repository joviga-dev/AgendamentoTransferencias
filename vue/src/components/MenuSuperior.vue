<script setup lang="ts">
import Menubar from 'primevue/menubar';
import { ref } from "vue";
import { useRouter } from 'vue-router';

const router = useRouter();
const items = ref([
  {
    label: 'Transferir',
    icon: 'pi pi-send',
    command: () => {
      router.push('/transferir');
    }
  },
  {
    label: 'Extrato',
    icon: 'pi pi-receipt',
    command: () => {
      router.push('/extrato');
    }
  },
]);
</script>

<template>
  <div class="card">
    <Menubar :model="items" class="fluid"/>
  </div>
</template>

<style scoped>

</style>
