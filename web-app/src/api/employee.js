import service from "../utils/request";

export function FindAllEmployee(){
    return service({
        url: '/employee',
        method: 'get'
    })
}

export function SaveEmployee(data) {
    return service({
        url: '/employee',
        method: 'post',
        data: data
    })
}

export function DeleteEmployeeById(id){
    return service({
        url: '/employee?id=' + id,
        method: 'delete'
    })
}