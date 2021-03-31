import service from "../utils/request";

export function IsInit() {
    return service({
        url: '/admin/hasInit',
        method: 'get',
    })
}

export function Init(data) {
    return service({
        url: '/admin/init',
        method: 'post',
        data: data
    })
}

export function DeleteAdmin(id) {
    return service({
        url: '/admin?id=' + id,
        method: 'delete',
    })
}

export function FindAllAdmin() {
    return service({
        url: '/admin',
        method: 'get',
    })
}

export function SaveAdmin(data) {
    return service({
        url: '/admin',
        method: 'post',
        data: data
    })
}

export function AdminSendEmail(email) {
    return service({
        url: '/admin/sendEmail?email=' + email,
        method: 'get'
    })
}

export function AdminLogin(type, data) {
    return service({
        url: '/admin/login?type=' + type,
        method: 'post',
        data: data
    })
}