import Vue from 'vue'
import VueRouter from 'vue-router'
import store from '../store'
import routes from "@/router/routes"
import NProgress from 'nprogress'
import 'nprogress/nprogress.css'

//顶部进度条样式
NProgress.configure({
    showSpinner: false,
    speed: 800,
});

Vue.use(VueRouter)

const router = new VueRouter({
    mode: 'history',
    base: process.env.BASE_URL,
    routes
})

//路由卫士
router.beforeEach((to, from, next) => {
    NProgress.start()
    to.meta.auth && !store.state.user.token ? next("/login") : next()
})

router.afterEach(() => {
    NProgress.done()
})

export default router
