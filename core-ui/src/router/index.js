import Vue from 'vue'
import VueRouter from 'vue-router'
Vue.use(VueRouter)

const EditNote = () => import('@/views/note/EditNote')
const Home     = () => import('@/views/home/Home')
const ViewNote = () => import('@/views/note/ViewNote')
const NoteList = () => import('@/views/note/NoteList')
const IndexPage= () => import('@/views/IndexPage')
const MindMapEdit = () => import('@/views/mindmap/MindMapEdit')
const MindMapList = () => import('@/views/mindmap/MindMapList')
const Login    = () => import('@/views/sys/Login')
const Profile  = () => import('@/views/profile/Profile')

const routes = [
  {
    path: '',
    redirect: '/index',
    meta: {
      title: '欢迎来到Galaxy博客'
    }
  },
  {
    path: '/index',
    component: IndexPage,
    children: [
      {
        path: '',
        redirect: 'home',
        meta: {
          title: '欢迎来到Galaxy博客'
        }
      },
      {
        path: 'home',
        component: Home
      },
      {
        path: 'editNote',
        component: EditNote
      },
      {
        path: 'noteList',
        component: NoteList
      },
      {
        path: 'mindMapEdit',
        component: MindMapEdit
      },
      {
        path: 'mindMapList',
        component: MindMapList
      },
      {
        path: 'profile',
        component: Profile
      }
    ]
  },
  {
    path: '/note',
    component: ViewNote
  },
  {
    path: '/login',
    component: Login
  }
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
