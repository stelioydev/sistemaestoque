import { reactive } from 'vue'

const defaultProducts = [
  { id: 1, name: 'Teclado Mecânico', category: 'Periféricos', supplier: 'Tech Supply', price: 289.90, qty: 25, min: 8, active: true },
  { id: 2, name: 'Mouse sem fio', category: 'Periféricos', supplier: 'Tech Supply', price: 119.90, qty: 7, min: 10, active: true },
  { id: 3, name: 'Monitor 24”', category: 'Monitores', supplier: 'Vision Distribuidora', price: 899.90, qty: 14, min: 5, active: true },
  { id: 4, name: 'Cabo HDMI 2m', category: 'Cabos', supplier: 'Conecta Brasil', price: 39.90, qty: 2, min: 6, active: true },
  { id: 5, name: 'Headset USB', category: 'Áudio', supplier: 'Tech Supply', price: 179.90, qty: 19, min: 5, active: true }
]
const defaultCategories = [
  { id: 1, name: 'Periféricos', description: 'Teclados, mouses e acessórios', active: true },
  { id: 2, name: 'Monitores', description: 'Monitores e displays', active: true },
  { id: 3, name: 'Cabos', description: 'Cabos e conectores', active: true },
  { id: 4, name: 'Áudio', description: 'Fones e headsets', active: true }
]
const defaultSuppliers = [
  { id: 1, name: 'Tech Supply', cnpj: '12.345.678/0001-10', email: 'contato@techsupply.com', phone: '(61) 3333-1000', active: true },
  { id: 2, name: 'Vision Distribuidora', cnpj: '45.321.876/0001-55', email: 'vendas@vision.com', phone: '(61) 3333-2000', active: true },
  { id: 3, name: 'Conecta Brasil', cnpj: '98.765.432/0001-90', email: 'comercial@conecta.com', phone: '(61) 3333-3000', active: true }
]
const defaultMovements = [
  { id: 1, product: 'Teclado Mecânico', type: 'ENTRADA', qty: 10, date: new Date(Date.now()-86400000*2).toISOString(), user: 'Administrador', note: 'Reposição' },
  { id: 2, product: 'Mouse sem fio', type: 'SAÍDA', qty: 3, date: new Date(Date.now()-86400000).toISOString(), user: 'Administrador', note: 'Uso interno' },
  { id: 3, product: 'Monitor 24”', type: 'ENTRADA', qty: 4, date: new Date().toISOString(), user: 'Administrador', note: 'Compra mensal' }
]

function read(key, fallback){ try { return JSON.parse(localStorage.getItem(key)) ?? fallback } catch { return fallback } }
function save(key, value){ localStorage.setItem(key, JSON.stringify(value)) }

export const state = reactive({
  products: read('stockos_products', defaultProducts),
  categories: read('stockos_categories', defaultCategories),
  suppliers: read('stockos_suppliers', defaultSuppliers),
  movements: read('stockos_movements', defaultMovements),
  user: read('stockos_user', { name: 'Administrador', email: 'admin@stockos.com', role: 'Administrador do Sistema', access: 'Acesso total', avatar: '' })
})

export function persist(){
  save('stockos_products', state.products)
  save('stockos_categories', state.categories)
  save('stockos_suppliers', state.suppliers)
  save('stockos_movements', state.movements)
  save('stockos_user', state.user)
}
