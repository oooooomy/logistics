import service from "../utils/request";

export function FindAllSale() {
    return service({
        url: '/sale',
        method: 'get'
    })
}

export function SearchCompany(name) {
    return service({
        url: '/sale/search/' + name,
        method: 'get'
    })
}

export function SaveSale(data) {
    return service({
        url: '/sale',
        method: 'post',
        data: data
    })
}