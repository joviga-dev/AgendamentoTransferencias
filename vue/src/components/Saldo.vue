<script setup lang="ts">

import Card from 'primevue/card'
import Avatar from 'primevue/avatar'
import { onMounted, ref } from 'vue'
import { ApiService } from '@/service/ApiService.ts'

const url = "/api/usuario";
const saldo = ref<number>();
const id = ref<number>(1);
onMounted(() => {
  ApiService.get(`${url}/id/${id.value}/saldo`)
    .then(({data}) => {
      saldo.value = data;
    })
})
</script>

<template>
  <Card class="my-5">
    <template #content>
      <div class="grid nested-grid">
        <div class="col-auto">
          <div class="col">
            <Avatar icon="pi pi-user" class="mr-2" size="xlarge" shape="circle" />
          </div>
        </div>
        <div class="grid">
          <div class="col-10">
            <h2>João Vitor Garcia</h2>
          </div>
          <div class="col">
            <h3>Saldo: {{ saldo }}</h3>
          </div>
        </div>
      </div>
    </template>
  </Card>
</template>

<style scoped>

</style>
