import request from "axios"
import {message} from 'ant-design-vue'
import router from '../router/index'

const token = localStorage.getItem("token")

const service = request.create({
    baseURL: 'http://localhost:8080/api',
    timeout: 50000,
    headers: {'Authorization': token}
});

service.interceptors.response.use(
    response => {

        const res = response.data;

        //判断response状态
        if (!res.status) message.error(res.msg)

        return res
    },

    error => {
        message.error(error)
        router.push('/500')
        return Promise.reject(error)
    }
);

export default service