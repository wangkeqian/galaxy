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
const Profile  = () => import('@/views/profile/Profile')
const SignUp = () => import('@/views/sys/SignUp')
const SignIn = () => import('@/views/sys/SignIn')
const test = () => import('@/views/sys/LoginRegister')
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
    path: '/sign_up',
    component: SignUp 
  },
  {
    path: '/blog',
    component: test ,
    children: [
      {
        path: '',
        redirect: 'sign_in',
        meta: {
          title: '登录'
        }
      },
      {
        path: 'sign_up',
        component: SignUp
      },
      {
        path: 'sign_in',
        component: SignIn
      },
    ]
  }
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
