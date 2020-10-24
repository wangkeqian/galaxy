import Vue from 'vue'
import VueRouter from 'vue-router'
Vue.use(VueRouter)

const EditNote = () => import('@/views/note/EditNote')
const Home     = () => import('@/views/home/Home')
const ViewNote = () => import('@/views/note/ViewNote')
const NoteList = () => import('@/views/note/NoteList')

const routes = [
  {
    path: '',
    component: Home
  },
  {
    path: '/home',
    component: Home
  },
  {
    path: '/editNote',
    component: EditNote
  },
  {
    path: '/viewNote',
    component: ViewNote
  },
  {
    path: '/noteList',
    component: NoteList
  }
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
