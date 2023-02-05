import {HOST} from '../../commons/hosts';
import RestApiClient from "../../commons/api/rest-client";

const endpoint = {
    get_caregivers: '/caregiver/',
    post_caregivers: "/caregiver/",
    put_caregivers: "/caregiver/",
    login_caregivers: "/caregiver/login/",
    add_patient_caregivers: "/addpatient/",
    delete_caregivers: "/caregiver/"
};

function getCaregivers(callback){
    let request = new Request(HOST.backend_api+endpoint.get_caregivers, {
        method: 'GET',
    });

    RestApiClient.performRequest(request,callback);
}

function getCaregiverById(params, callback){
    let request = new Request(HOST.backend_api+endpoint.get_caregivers + params.id, {
        method: 'GET',
    });
    
    RestApiClient.performRequest(request, callback);
}

function postCaregiver(caregiver, callback){
    let request = new Request(HOST.backend_api+endpoint.post_caregivers, {
        method: 'POST',
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(caregiver)
    });

    RestApiClient.performRequest(request, callback);
}

function putCaregiver(params, caregiver, callback){
    let request = new Request(HOST.backend_api+endpoint.put_caregivers+params.id,{
        method: 'PUT',
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(caregiver)
    });

    RestApiClient.performRequest(request, callback);
}

function loginCaregiver(loginPair, callback){
    let request = new Request(HOST.backend_api+endpoint.login_caregivers,{
        method: 'PUT',
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(loginPair)
    });

    RestApiClient.performRequest(request, callback);
}

function addPatientCaregiver(p,params,callback){
    let request = new Request(HOST.backend_api + endpoint.post_caregivers + params.id + endpoint.add_patient_caregivers,{
        method: 'POST',
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(p)
    });

    RestApiClient.performRequest(request, callback);
}

function deleteCaregiver(cg, callback){
    let request = new Request(HOST.backend_api+endpoint.delete_caregivers,{
        method: 'DELETE',
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(cg)
    });

    RestApiClient.performRequest(request, callback);
}

export {
    getCaregivers,
    getCaregiverById,
    postCaregiver,
    putCaregiver,
    loginCaregiver,
    addPatientCaregiver,
    deleteCaregiver
}