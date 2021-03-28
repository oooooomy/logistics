import service from "../utils/request";

export function FindAllEmployee(){
    return service({
        url: '/employee/findAll',
        method: 'get'
    })
}

export function SaveEmployee(data) {
    return service({
        url: '/employee/save',
        method: 'post',
        data: data
    })
}

export function DeleteEmployeeById(id){
    return service({
        url: '/employee/delete?id=' + id,
        method: 'delete'
    })
}