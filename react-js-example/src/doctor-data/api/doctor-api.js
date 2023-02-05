import {HOST} from '../../commons/hosts';
import RestApiClient from "../../commons/api/rest-client";

const endpoint = {
    get_doctors: '/doctor/',
    post_doctors: "/doctor/",
    put_doctors: "/doctor/",
    login_doctors: "/doctor/login/",
    get_daily_activities: "/doctor/activityStatuses/",
    get_medciation_status: "/doctor/dailyRecords/",
    post_recommendation: "/doctor/recommend/"
};

function getDoctors(callback){
    let request = new Request(HOST.backend_api+endpoint.get_doctors, {
        method: 'GET',
    });

    RestApiClient.performRequest(request,callback);
}

function getDoctorById(params, callback){
    let request = new Request(HOST.backend_api+endpoint.get_doctors + params.id, {
        method: 'GET',
    });

    RestApiClient.performRequest(request, callback);
}

function postDoctor(doctor, callback){
    let request = new Request(HOST.backend_api+endpoint.post_doctors, {
        method: 'POST',
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(doctor)
    });

    RestApiClient.performRequest(request, callback);
}

function putDoctor(params, doctor, callback){
    let request = new Request(HOST.backend_api+endpoint.put_doctors+params.id,{
        method: 'PUT',
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(doctor)
    });

    RestApiClient.performRequest(request, callback);
}

function loginDoctor(loginPair, callback){
    let request = new Request(HOST.backend_api+endpoint.login_doctors,{
        method: 'PUT',
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(loginPair)
    });

    RestApiClient.performRequest(request, callback);
}

function getPatientActivity(id, callback){
    let request = new Request(HOST.backend_api+endpoint.get_daily_activities + id, {
        method: 'GET',
    });

    RestApiClient.performRequest(request, callback);
}

function getPatientMedHistory(id, callback){
    let request = new Request(HOST.backend_api+endpoint.get_medciation_status + id, {
        method: 'GET',
    });

    RestApiClient.performRequest(request, callback);
}

function postRecommendation(rec, callback){
    let request = new Request(HOST.backend_api+endpoint.post_recommendation, {
        method: 'POST',
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(rec)
    });

    RestApiClient.performRequest(request, callback);
}

export {
    getDoctors,
    getDoctorById,
    postDoctor,
    putDoctor,
    loginDoctor,
    getPatientActivity,
    getPatientMedHistory,
    postRecommendation
}