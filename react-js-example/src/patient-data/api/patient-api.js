import {HOST} from '../../commons/hosts';
import RestApiClient from "../../commons/api/rest-client";

const endpoint = {
    get_patients: '/patient/',
    post_patients: "/patient/",
    put_patients: "/patient/",
    login_patients: "/patient/login/",
    delete_patients: "/patient/"
};

function getPatients(callback){
    let request = new Request(HOST.backend_api+endpoint.get_patients, {
        method: 'GET',
    });

    RestApiClient.performRequest(request,callback);
}

function getPatientById(params, callback){
    let request = new Request(HOST.backend_api+endpoint.get_patients + params.id, {
        method: 'GET',
    });

    RestApiClient.performRequest(request, callback);
}

function postPatient(patient, callback){
    let request = new Request(HOST.backend_api+endpoint.post_patients, {
        method: 'POST',
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(patient)
    });

    RestApiClient.performRequest(request, callback);
}

function putPatient(patient, callback){
    let request = new Request(HOST.backend_api+endpoint.put_patients,{
        method: 'PUT',
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(patient)
    });

    RestApiClient.performRequest(request, callback);
}

function loginPatient(loginPair, callback){
    let request = new Request(HOST.backend_api+endpoint.login_patients,{
        method: 'PUT',
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(loginPair)
    });

    RestApiClient.performRequest(request, callback);
}

function deletePatient(pat, callback){
    let request = new Request(HOST.backend_api+endpoint.delete_patients,{
        method: 'DELETE',
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(pat)
    });

    RestApiClient.performRequest(request, callback);
}

export {
    getPatients,
    getPatientById,
    postPatient,
    putPatient,
    loginPatient,
    deletePatient
}