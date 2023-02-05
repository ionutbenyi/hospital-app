import {HOST} from '../../commons/hosts';
import RestApiClient from "../../commons/api/rest-client";

const endpoint = {
    get_patients: '/patient/daily/get_patients'
};

function getPatients(callback){
    let request = new Request(HOST.backend_api+endpoint.get_patients, {
        method: 'POST',
    });

    RestApiClient.performRequest(request,callback);
}

export {
    getPatients
}
