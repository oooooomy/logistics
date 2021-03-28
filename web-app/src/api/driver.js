import service from "../utils/request";

export function FindAllDriver(){
    return service({
        url: '/driver/findAll',
        method: 'get'
    })
}

export function SaveDriver(data) {
    return service({
        url: '/driver/save',
        method: 'post',
        data: data
    })
}

export function DeleteDriverById(id){
    return service({
        url: '/driver/delete?id=' + id,
        method: 'delete'
    })
}