<script setup lang="ts">
import Card from 'primevue/card'
import Column from 'primevue/column'
import DataTable from 'primevue/datatable'
import { onMounted, ref } from 'vue'
import { ApiService } from '@/service/ApiService.ts'
import { ExtratoTransferencia } from '@/model/ExtratoTransferencia.ts'

const url = '/api/agendamento'
const agendamentos = ref<Array<ExtratoTransferencia>>([])
const contaOrigem = ref<string>('0000000001')

onMounted(async () => {
  await ApiService.get(`${url}/conta-origem/${contaOrigem.value}/listar`)
    .then(({ data }) => {
      agendamentos.value = data
    })
})
</script>

<!--@TODO - Implementar Lazy Loading -->
<template>
  <Card class="corpo">
    <template #title>Extrato de Transferências</template>
    <template #content>
      <DataTable :value="agendamentos" paginator stripedRows sortMode="multiple" :rows="5"
                 :rowsPerPageOptions="[5, 10, 20, 50]">
        <Column header="Conta Destino" sortable field="contaDestino"></Column>
        <Column header="Data Agendamento" sortable field="dataAgendamento"></Column>
        <Column header="Data Transferência" sortable field="dataTransferencia"></Column>
        <Column header="Valor Transferência" sortable field="valorTransferencia"></Column>
        <Column header="Taxa (%)" sortable field="taxa"></Column>
        <Column header="Taxa (Valor Base)" sortable field="valorBase"></Column>
        <Column header="Valor Final" sortable field="valorFinal"></Column>
      </DataTable>
    </template>
  </Card>
</template>

<style scoped>

.corpo {
  width: 1200px;
  max-height: 500px;
}
</style>
