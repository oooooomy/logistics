import service from "../utils/request";

export function FindAllDistribution() {
    return service({
        url: '/distribution',
        method: 'get'
    })
}

export function FindAllCanUse() {
    return service({
        url: '/distribution/can',
        method: 'get'
    })
}

export function SaveDistribution(data) {
    return service({
        url: '/distribution',
        method: 'post',
        data: data
    })
}