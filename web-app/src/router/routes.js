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
                path: 'warehouse/:id',
                component: () => import('@/views/basics/Details'),
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
                path: 'sale/create',
                component: () => import('@/views/sale/Create'),
                meta: {auth: true}
            },

            {
                path: 'delivery/create',
                component: () => import('@/views/delivery/Create'),
                meta: {auth: true}
            },
            {
                path: 'delivery/list',
                component: () => import('@/views/delivery/List'),
                meta: {auth: true}
            },

            {
                path: 'analyze/in',
                component: () => import('@/views/analyze/In'),
                meta: {auth: true}
            },

            {
                path: 'analyze/out',
                component: () => import('@/views/analyze/Out'),
                meta: {auth: true}
            },

            {
                path: 'security',
                component: () => import('@/views/system/Security'),
                meta: {auth: true},
            },
            {
                path: 'admin',
                component: () => import('@/views/system/Admin'),
                meta: {auth: true},
            },
            {
                path: 'role',
                component: () => import('@/views/system/Role'),
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

    //初始化
    {
        path: '/init',
        name: 'Init',
        component: () => import('@/views/Init'),
        meta: {auth: false}
    },

    //404
    {
        path: "*",
        redirect: '/404'
    }

]

export default routes