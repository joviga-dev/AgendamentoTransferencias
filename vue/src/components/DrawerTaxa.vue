<script setup lang="ts">

import Column from 'primevue/column'
import ColumnGroup from 'primevue/columngroup'
import Drawer from 'primevue/drawer'
import DataTable from 'primevue/datatable'
import Row from 'primevue/row'
import type { TaxasListagem } from '@/model/TaxasListagem.ts'

const props = defineProps({
  visible: { required: true, type: Boolean },
  taxas: { required: true, type: Array<TaxasListagem> }
})
</script>

<template>
  <Drawer :visible="visible" @update:visible="visible = $event"  header="Entenda sua Taxa" position="right" class="w-2">
    <DataTable :value="taxas" stripedRows tableStyle="">
      <ColumnGroup type="header">
        <Row>
          <Column header="Intervalo (Dias)" :colspan="2"></Column>
        </Row>
        <Row>
          <Column header="De" field="intervaloInicio"></Column>
          <Column header="Até" field="intervaloInicio"></Column>
          <Column header="R$ (Valor Base)" field="intervaloInicio"></Column>
          <Column header="Taxa (%)" field="intervaloInicio"></Column>
        </Row>
      </ColumnGroup>
      <Column field="intervaloInicio"></Column>
      <Column field="intervaloFim"></Column>
      <Column field="valorBase"></Column>
      <Column field="taxa"></Column>
    </DataTable>
  </Drawer>
</template>

<style scoped>

</style>
