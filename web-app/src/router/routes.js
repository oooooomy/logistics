import Layout from '@/layout/Index'

const routes = [

    //公共布局下的路由
    {
        path: '/',
        component: Layout,
        children: [
            {
                path: "",
                redirect: "/commodity",
                meta: {auth: true}
            },
            {
                path: 'commodity',
                component: () => import('@/views/basics/Commodity'),
                meta: {auth: true}
            },
            {
                path: 'company',
                component: () => import('@/views/basics/Company'),
                meta: {auth: true}
            },
            {
                path: 'employee',
                component: () => import('@/views/basics/Employee'),
                meta: {auth: true}
            },
            {
                path: 'warehouse',
                component: () => import('@/views/basics/Warehouse'),
                meta: {auth: true}
            },
            {
                path: 'driver',
                component: () => import('@/views/transport/Driver'),
                meta: {auth: true}
            },
            {
                path: 'vehicle',
                component: () => import('@/views/transport/Vehicle'),
                meta: {auth: true}
            },
            {
                path: 'sale/record',
                component: () => import('@/views/sale/Record'),
                meta: {auth: true}
            },

            {
                path: 'security',
                component: () => import('@/views/system/Security'),
                meta: {auth: true},
            },

            //403
            {
                path: '403',
                component: () => import('@/views/error/403'),
                meta: {auth: true}
            },
            //404
            {
                path: '404',
                component: () => import('@/views/error/404'),
                meta: {auth: true}
            },
            //500
            {
                path: '500',
                component: () => import('@/views/error/500'),
                meta: {auth: true}
            },

        ]
    },

    //登录页
    {
        path: '/login',
        name: 'Login',
        component: () => import('@/views/Login'),
        meta: {auth: false}
    },

    //404
    {
        path: "*",
        redirect: '/404'
    }

]

export default routes