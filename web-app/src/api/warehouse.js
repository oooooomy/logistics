import service from "../utils/request";

export function FindAllWarehouse(){
    return service({
        url: '/warehouse/findAll',
        method: 'get'
    })
}

export function SaveWarehouse(data) {
    return service({
        url: '/warehouse/save',
        method: 'post',
        data: data
    })
}

export function DeleteWarehouseById(id){
    return service({
        url: '/warehouse/delete?id=' + id,
        method: 'delete'
    })
}