import service from "../utils/request";

export function FindAllWarehouse() {
    return service({
        url: '/warehouse',
        method: 'get'
    })
}

export function SaveWarehouse(data) {
    return service({
        url: '/warehouse',
        method: 'post',
        data: data
    })
}

export function DeleteWarehouseById(id) {
    return service({
        url: '/warehouse?id=' + id,
        method: 'delete'
    })
}