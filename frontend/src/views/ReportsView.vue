<script setup>
import { computed } from 'vue'
import { FileDown, Package, CircleDollarSign, ArrowDownToLine, ArrowUpFromLine, TriangleAlert } from 'lucide-vue-next'
import PageHeader from '../components/PageHeader.vue'
import { state } from '../data/store'

const totalValue = computed(() => state.products.reduce((sum, p) => sum + p.qty * p.price, 0))
const entries = computed(() => state.movements.filter(m => m.type === 'ENTRADA').reduce((sum, m) => sum + m.qty, 0))
const exits = computed(() => state.movements.filter(m => m.type === 'SAÍDA').reduce((sum, m) => sum + m.qty, 0))
const low = computed(() => state.products.filter(p => p.qty <= p.min))
const categoryRows = computed(() => state.categories.map(c => {
  const products = state.products.filter(p => p.category === c.name)
  return { name: c.name, count: products.length, units: products.reduce((s, p) => s + p.qty, 0), value: products.reduce((s, p) => s + p.qty * p.price, 0) }
}).filter(c => c.count))
const money = value => value.toLocaleString('pt-BR', { style: 'currency', currency: 'BRL' })

async function exportPdf() {
  const { jsPDF } = await import('jspdf')
  const { default: autoTable } = await import('jspdf-autotable')
  const doc = new jsPDF()
  doc.setFillColor(11, 18, 32); doc.rect(0, 0, 210, 34, 'F')
  doc.setTextColor(57, 147, 255); doc.setFontSize(22); doc.text('StockOS', 14, 16)
  doc.setTextColor(235, 242, 255); doc.setFontSize(11); doc.text('Relatorio consolidado de estoque', 14, 25)
  doc.setTextColor(45, 55, 70); doc.setFontSize(9)
  doc.text(`Gerado em: ${new Date().toLocaleString('pt-BR')}`, 14, 43)
  doc.text('Responsavel: Administrador', 14, 49)
  doc.setFontSize(11); doc.text(`Valor total: ${money(totalValue.value)}`, 14, 59)
  doc.text(`Entradas: ${entries.value}   Saidas: ${exits.value}   Itens em atencao: ${low.value.length}`, 14, 67)
  autoTable(doc, { startY: 76, head: [['Produto','Categoria','Qtd.','Min.','Preco','Valor total']], body: state.products.map(p => [p.name,p.category,p.qty,p.min,money(p.price),money(p.qty*p.price)]), styles: { fontSize: 8 }, headStyles: { fillColor: [32,109,241] } })
  doc.save(`relatorio-stockos-${new Date().toISOString().slice(0,10)}.pdf`)
}
</script>

<template>
  <PageHeader title="Relatórios" subtitle="Visão consolidada e atualizada do estoque.">
    <button class="btn btn-primary" @click="exportPdf"><FileDown :size="18"/>Exportar PDF</button>
  </PageHeader>
  <div class="stats-grid">
    <div class="stat-card"><span class="stat-icon"><Package/></span><div><small>Produtos cadastrados</small><strong>{{state.products.length}}</strong></div></div>
    <div class="stat-card success"><span class="stat-icon"><CircleDollarSign/></span><div><small>Valor do estoque</small><strong class="report-money">{{money(totalValue)}}</strong></div></div>
    <div class="stat-card success"><span class="stat-icon"><ArrowDownToLine/></span><div><small>Total de entradas</small><strong>{{entries}}</strong></div></div>
    <div class="stat-card info"><span class="stat-icon"><ArrowUpFromLine/></span><div><small>Total de saídas</small><strong>{{exits}}</strong></div></div>
  </div>
  <div class="dashboard-grid reports-grid">
    <section class="panel"><div class="panel-title"><div><h2>Valor por categoria</h2><p>Distribuição financeira e quantidade disponível.</p></div></div><div class="table-wrap"><table><thead><tr><th>Categoria</th><th>Produtos</th><th>Unidades</th><th>Valor</th></tr></thead><tbody><tr v-for="c in categoryRows" :key="c.name"><td><b>{{c.name}}</b></td><td>{{c.count}}</td><td>{{c.units}}</td><td class="money-cell">{{money(c.value)}}</td></tr></tbody></table></div></section>
    <section class="panel"><div class="panel-title"><div><h2>Itens que requerem atenção</h2><p>Produtos no estoque mínimo ou abaixo.</p></div><TriangleAlert class="attention-icon"/></div><div class="low-list"><div v-for="p in low" :key="p.id" class="low-item"><div><b>{{p.name}}</b><small>{{p.category}}</small></div><div class="low-qty"><strong>{{p.qty}}</strong><small>mín. {{p.min}}</small></div></div><div v-if="!low.length" class="empty">Nenhum item em situação crítica.</div></div></section>
  </div>
</template>
