import service from "../utils/request";

export function FindAllRole(){
    return service({
        url: '/role',
        method: 'get'
    })
}
