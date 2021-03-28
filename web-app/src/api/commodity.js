import service from "../utils/request";

export function FindAllCommodity(){
    return service({
        url: '/commodity/findAll',
        method: 'get'
    })
}

export function SaveCommodity(data) {
    return service({
        url: '/commodity/save',
        method: 'post',
        data: data
    })
}

export function DeleteCommodityById(id){
    return service({
        url: '/commodity/delete?id=' + id,
        method: 'delete'
    })
}