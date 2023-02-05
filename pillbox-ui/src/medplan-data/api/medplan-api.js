import {HOST} from '../../commons/hosts';
import RestApiClient from "../../commons/api/rest-client";

const endpoint = {
    get_medicines: '/patient/daily/get_list/',
    send_confirm: '/patient/daily/confirm/',
    send_infirm: '/patient/daily/infirm/'
};

function getMedicines(params,callback){
    let request = new Request(HOST.backend_api+endpoint.get_medicines+params.uid, {
        method: 'POST',
    });

    RestApiClient.performRequest(request,callback);
}

function sendTakenConfirm(userId,medName,callback){
    let request = new Request(HOST.backend_api+endpoint.send_confirm+userId+"/"+medName, {
        method: 'POST',
    });

    RestApiClient.performRequest(request,callback);
}

function sendNotTakenConfirm(userId,medName,callback){
    let request = new Request(HOST.backend_api+endpoint.send_infirm+userId+"/"+medName, {
        method: 'POST',
    });

    RestApiClient.performRequest(request,callback);
}

export {
    getMedicines,
    sendTakenConfirm,
    sendNotTakenConfirm
}
