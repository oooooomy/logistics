import service from "../utils/request";

export function FindAllInventory(id) {
    return service({
        url: '/inventory/warehouse/' + id,
        method: 'get'
    })
}

export function InAndOut(type, data) {
    return service({
        url: '/inventory/' + type,
        method: 'post',
        data: data
    })
}

export function FindRecordByWarehouse(id) {
    return service({
        url: '/inventory/record/warehouse/' + id,
        method: 'get'
    })
}

export function DeleteInventoryById(id) {
    return service({
        url: '/inventory?id=' + id,
        method: 'delete'
    })
}