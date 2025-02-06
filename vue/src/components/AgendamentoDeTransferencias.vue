<script setup lang="ts">
import Card from 'primevue/card'
import { onMounted, ref } from 'vue'
import FloatLabel from 'primevue/floatlabel'
import InputText from 'primevue/inputtext'
import DatePicker from 'primevue/datepicker'
import InputNumber from 'primevue/inputnumber'
import RevisaoDaTransferencia from '@/components/RevisaoDaTransferencia.vue'
import * as yup from 'yup'
import { ErrorMessage, Field, Form } from 'vee-validate'
import Stepper from 'primevue/stepper'
import StepList from 'primevue/steplist'
import StepPanels from 'primevue/steppanels'
import Step from 'primevue/step'
import Button from 'primevue/button'
import InputMask from 'primevue/inputmask'
import StepPanel from 'primevue/steppanel'
import moment from 'moment'
import Toast from 'primevue/toast'
import { useToast } from 'primevue'
import { AgendamentoTransferencia } from '@/model/AgendamentoTransferencia.ts'
import { ApiService } from '@/service/ApiService.ts'
import { TaxaAplicada } from '@/model/TaxaAplicada.ts'
import Saldo from '@/components/Saldo.vue'
import { useRouter } from 'vue-router'

const url = '/api/agendamento'
const agendamento = ref<AgendamentoTransferencia>(new AgendamentoTransferencia())
const contaOrigem = ref<string>('0000000001')
const agendamentoKey = ref<number>(0)
const toast = useToast()
const taxa = ref<TaxaAplicada>(new TaxaAplicada())
const router = useRouter()

const schema = yup.object().shape({
  contaDestino: yup.string().required('Campo Obrigatório'),
  dataTransferencia: yup.date().required('Campo Obrigatório'),
  valorTransferencia: yup.number().positive('Deve ser um valor positivo').required('Campo Obrigatório')
})

const formatarData = (dataString: string): string => {
  return new Date(dataString).toLocaleDateString('pt-BR')
}

const onClickProximo = async (proximoStep: string, activateCallback: Function) => {
  if (agendamento.value.contaOrigem === agendamento.value.contaDestino) {
    toast.add({
      severity: 'error',
      summary: 'Erro',
      detail: 'Não é possível realizar transferências para sua própria conta',
      life: 3000
    })
    return
  }
  await aplicarTaxa()
  agendamento.value.dataTransferencia = formatarData(agendamento.value.dataTransferencia.toString())
  activateCallback(proximoStep)
  toast.add({
    severity: 'warn',
    summary: 'Atenção',
    detail: 'Revise suas informações antes de continuar',
    life: 3000
  })
}

const aplicarTaxa = async () => {
  const data = {
    intervaloInicio: agendamento.value.dataAgendamento,
    intervaloFim: formatarData(agendamento.value.dataTransferencia),
    valorTransferencia: formataValor(agendamento.value.valorTransferencia)
  }
  await ApiService.post(`/api/taxa/buscar`, data)
    .then(({ data }) => {
      taxa.value = data
      agendamento.value.valorFinal = taxa.value.valorFinal
      agendamento.value.taxa = taxa.value.taxa
      agendamento.value.valorBase = taxa.value.valorBase
    })
    .catch((error) => {
      toast.add({ severity: 'error', summary: 'Erro', detail: error, life: 5000 })
    })
}

const formataValor = (valor: string) => {
  return parseFloat(valor.replace(/[R$\s.]/g, '').replace(',', '.'))
}

// Tive alguns problemas com a validação e o componente InputNumber do PrimeVue, então tive que
// setar os valores manualmente nos refs
const setarValoresInputNumber = (event: any) => {
  agendamento.value.valorTransferencia = event.value
}

const onClickFinalizarAgendamento = async () => {
  agendamento.value.valorTransferencia = formataValor(agendamento.value.valorTransferencia)
  await ApiService.post(`${url}/agendar-transferencia`, agendamento.value)
    .then(() => {
      agendamentoKey.value++;
      toast.add({
        severity: 'success',
        summary: 'Sucesso',
        detail: 'Agendamento realizado com sucesso!',
        life: 5000
      })
    })
    .catch((error) => {
      toast.add({ severity: 'error', summary: 'Erro', detail: error, life: 5000 })
    })

}

onMounted(() => {
  agendamento.value.dataAgendamento = moment().format('DD/MM/yyyy')
})
</script>

<template>
  <div class="d-flex justify-content-center" :key="agendamentoKey">
    <Saldo></Saldo>
    <Card class="corpo">
      <template #title>Agendamento de Transferências</template>
      <template #content>
        <Stepper value="1" linear>
          <StepList>
            <Step value="1">Informações</Step>
            <Step value="2">Revisão</Step>
          </StepList>
          <StepPanels>
            <StepPanel v-slot="{ activateCallback }" value="1">
              <Form @submit="onClickProximo('2', activateCallback)" ref="formRef"
                    class="form" :validation-schema="schema"
              >
                <div style="height: 200px">
                  <div class="formgrid grid">
                    <div class="field col-6">
                      <FloatLabel>
                        <InputText id="contaOrigem" v-model="contaOrigem"
                                   disabled />
                        <label for="contaOrigem">Conta Origem</label>
                      </FloatLabel>
                    </div>
                    <div class="field col-6">
                      <Field name="contaDestino" v-slot="{ field, meta, errors  }">
                        <FloatLabel>
                          <InputMask id="contaDestino" v-model="agendamento.contaDestino"
                                     v-bind="field" :invalid="!!errors.length && meta.touched"
                                     mask="9999999999" />
                          <label for="contaDestino">Conta Destino</label>
                        </FloatLabel>
                      </Field>
                      <ErrorMessage class="error" name="contaDestino" />
                    </div>
                    <div class="field col">
                      <Field name="valorTransferencia" v-slot="{  value,handleChange, errors }">
                        <FloatLabel>
                          <InputNumber inputId="valorTransferencia"
                                       :invalid="!!errors.length" :modelValue="value"
                                       @blur="setarValoresInputNumber($event)"
                                       @update:modelValue="handleChange"
                                       :minFractionDigits="2"
                                       :maxFractionDigits="2"
                                       mode="currency"
                                       currency="BRL"
                                       :useGrouping="false"
                                       fluid />
                          <label for="valorTransferencia">Valor</label>
                        </FloatLabel>
                      </Field>
                      <ErrorMessage class="error" name="valorTransferencia" />
                    </div>
                    <div class="field col">
                      <FloatLabel>
                        <label for="dataAgendamento">Data de Agendamento</label>
                        <InputText id="dataAgendamento" disabled
                                   v-model="agendamento.dataAgendamento" />
                      </FloatLabel>
                    </div>
                    <div class="field col">
                      <Field name="dataTransferencia" v-slot="{ field, meta, errors }">
                        <FloatLabel>
                          <label for="dataTransferencia">Data da Transferência</label>
                          <DatePicker id="dataTransferencia" v-model="agendamento.dataTransferencia"
                                      v-bind="field" dateFormat="dd/mm/yy"
                                      class="w-full" :invalid="!!errors.length && meta.touched" />
                        </FloatLabel>
                      </Field>
                      <ErrorMessage class="error" name="dataTransferencia" />
                    </div>
                  </div>
                </div>
                <div class="flex justify-content-end flex-wrap">
                  <Button label="Próximo" icon="pi pi-arrow-right" iconPos="right" type="submit" />
                </div>
              </Form>
            </StepPanel>
            <StepPanel v-slot="{ activateCallback }" value="2">
              <div style="height: 200px">
                <RevisaoDaTransferencia
                  :conta-destino="agendamento.contaDestino"
                  :conta-origem="agendamento.contaOrigem"
                  :data-agendamento="agendamento.dataAgendamento"
                  :data-transferencia="agendamento.dataTransferencia"
                  :valor-transferencia="agendamento.valorTransferencia"
                  :valor-final="agendamento.valorFinal"
                  :taxa="agendamento.taxa"
                  :valor-base="agendamento.valorBase">
                </RevisaoDaTransferencia>
              </div>
              <div class="flex justify-content-between flex-wrap">
                <Button label="Voltar" severity="secondary" icon="pi pi-arrow-left" class="mr-5"
                        @click="activateCallback('1')" />
                <Button label="Finalizar Agendamento" icon="pi pi-check" iconPos="right"
                        @click.prevent="onClickFinalizarAgendamento()"
                />
              </div>
            </StepPanel>
          </StepPanels>
        </Stepper>
      </template>
    </Card>
  </div>
</template>

<style scoped>

.corpo {
  width: 1200px;
  height: 380px;
}
</style>
