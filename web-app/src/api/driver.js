import service from "../utils/request";

export function FindAllDriver(){
    return service({
        url: '/driver',
        method: 'get'
    })
}

export function SaveDriver(data) {
    return service({
        url: '/driver',
        method: 'post',
        data: data
    })
}

export function DeleteDriverById(id){
    return service({
        url: '/driver?id=' + id,
        method: 'delete'
    })
}