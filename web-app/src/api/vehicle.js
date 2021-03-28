import service from "../utils/request";

export function FindAllVehicle(){
    return service({
        url: '/vehicle/findAll',
        method: 'get'
    })
}

export function SaveVehicle(data) {
    return service({
        url: '/vehicle/save',
        method: 'post',
        data: data
    })
}

export function DeleteVehicleById(id){
    return service({
        url: '/vehicle/delete?id=' + id,
        method: 'delete'
    })
}