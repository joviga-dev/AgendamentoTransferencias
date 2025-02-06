import { createRouter, createWebHistory } from 'vue-router'
import AgendamentoDeTransferencias from '@/components/AgendamentoDeTransferencias.vue'
import Extrato from '@/components/Extrato.vue'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: 'inicio',
      component: AgendamentoDeTransferencias,
    },
    {
      path: '/transferir',
      name: 'transferir',
      component: AgendamentoDeTransferencias,
    },
    {
      path: '/extrato',
      name: 'extrato',
      component: Extrato,
    },
  ],
})

export default router
