<script setup lang="ts">
import Card from 'primevue/card'
import Drawer from 'primevue/drawer'
import { ref } from 'vue'
import { ApiService } from '@/service/ApiService.ts'
import { AgendamentoTransferencia } from '@/model/AgendamentoTransferencia.ts'
import type { TaxasListagem } from '@/model/TaxasListagem.ts'
import DataTable from 'primevue/datatable'
import Column from 'primevue/column'
import ColumnGroup from 'primevue/columngroup'
import Row from 'primevue/row'
import { TaxaAplicada } from '@/model/TaxaAplicada.ts'
import DrawerTaxa from '@/components/DrawerTaxa.vue'

const props = defineProps({
  contaOrigem: { value: String, required: true },
  contaDestino: { value: String, required: true },
  dataTransferencia: { value: String, required: true },
  dataAgendamento: { value: String, required: true },
  valorTransferencia: { value: String, required: true },
  valorFinal: { value: String, required: true },
  taxa: { value: String, required: true },
  valorBase: { value: String, required: true },
})
const url = '/api/taxa'
const visible = ref(false)
const taxas = ref<Array<TaxasListagem>>([])

const onClickTaxa = async () => {
  visible.value = true
  await ApiService.get(`${url}/listar`)
    .then(({ data }) => {
      taxas.value = data
    })
}
</script>

<template>
  <div class="grid w-100">
    <div class="col-4">
      <Card>
        <template #title>
          <div class="flex justify-content-between flex-wrap">
            <div>
              Beneficiário
            </div>
            <div>
              <i class="pi pi-user" style="color: dodgerblue" />
            </div>
          </div>
        </template>
        <template #content>
          <div class="grid">
            <div class="col-12">
              <h3>Nome: Fulano da Silva</h3>
            </div>
            <div class="col-12">
              <h3>Conta Destino: {{ contaDestino }}</h3>
            </div>
          </div>
        </template>
      </Card>
    </div>
    <div class="col-4">
      <Card>
        <template #title>
          <div class="flex justify-content-between flex-wrap">
            <div>
              Data
            </div>
            <div>
              <i class="pi pi-calendar" style="color: goldenrod" />
            </div>
          </div>
        </template>
        <template #content>
          <div class="grid">
            <div class="col-12">
              <h3>Agendamento: {{ dataAgendamento }} (Hoje)
              </h3>
            </div>
            <div class="col-12">
              <h3>Transferência: {{ dataTransferencia }}
              </h3>
            </div>
          </div>
        </template>
      </Card>
    </div>
    <div class="col-4">
      <Card>
        <template #title>
          <div class="flex justify-content-between flex-wrap">
            <div>
              Valor Final
            </div>
            <div>
              <i class="pi pi-money-bill" style="color: green" />
            </div>
          </div>
        </template>
        <template #content>
          <div class="grid">
            <div class="col-12">
              <h3>Ao beneficiário: {{ valorTransferencia }}</h3>
            </div>
            <div class="col-6">
              <h4><a href="#" class="underline" @click="onClickTaxa">Taxa:</a> {{ taxa }}% + R$ {{ valorBase }}</h4>
            </div>
            <div class="col-6">
              <h4>Total: R$ {{ valorFinal }}</h4>
            </div>
          </div>
        </template>
      </Card>
    </div>
  </div>
  <DrawerTaxa :taxas="taxas" :visible="visible"></DrawerTaxa>
</template>

<style scoped>

</style>
