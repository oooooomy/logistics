import service from "../utils/request";

export function FindAllCommodity(){
    return service({
        url: '/commodity',
        method: 'get'
    })
}

export function SaveCommodity(data) {
    return service({
        url: '/commodity',
        method: 'post',
        data: data
    })
}

export function DeleteCommodityById(id){
    return service({
        url: '/commodity?id=' + id,
        method: 'delete'
    })
}