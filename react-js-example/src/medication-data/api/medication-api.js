import {HOST} from '../../commons/hosts';
import RestApiClient from "../../commons/api/rest-client";

const endpoint = {
    get_medication: '/medication/',
    post_medication: "/medication/",
    put_medication: "/medication/",
    delete_medication: "/medication/"
};

function getMedication(callback){
    let request = new Request(HOST.backend_api+endpoint.get_medication, {
        method: 'GET',
    });

    RestApiClient.performRequest(request,callback);
}

function getMedicationById(params, callback){
    let request = new Request(HOST.backend_api+endpoint.get_medication + params.id, {
        method: 'GET',
    });

    RestApiClient.performRequest(request, callback);
}

function postMedication(med, callback){
    let request = new Request(HOST.backend_api+endpoint.post_medication, {
        method: 'POST',
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(med)
    });

    RestApiClient.performRequest(request, callback);
}

function putMedication(params, med, callback){
    let request = new Request(HOST.backend_api+endpoint.put_medication+params.id,{
        method: 'PUT',
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(med)
    });

    RestApiClient.performRequest(request, callback);
}

function deleteMedication(med, callback){
    let request = new Request(HOST.backend_api+endpoint.delete_medication,{
        method: 'DELETE',
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(med)
    });

    RestApiClient.performRequest(request, callback);
}

export {
    getMedication,
    getMedicationById,
    postMedication,
    putMedication,
    deleteMedication
}