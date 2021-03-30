import service from "../utils/request";

export function FindAllVehicle(){
    return service({
        url: '/vehicle',
        method: 'get'
    })
}

export function SaveVehicle(data) {
    return service({
        url: '/vehicle',
        method: 'post',
        data: data
    })
}

export function DeleteVehicleById(id){
    return service({
        url: '/vehicle?id=' + id,
        method: 'delete'
    })
}