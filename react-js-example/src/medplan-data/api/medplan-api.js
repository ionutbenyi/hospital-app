import {HOST} from '../../commons/hosts';
import RestApiClient from "../../commons/api/rest-client";

const endpoint = {
    get_medication_plan: '/plans/',
    post_medication_plan: "/plans/",
    put_medication_plan: "/plans/",
    delete_medication_plan: "/plans/",
    find_medication_plan_by_nr: "/plans/number/"
};

function getMedicationPlans(callback){
    let request = new Request(HOST.backend_api+endpoint.get_medication_plan, {
        method: 'GET',
    });

    RestApiClient.performRequest(request,callback);
}

function getMedicationPlanById(params, callback){
    let request = new Request(HOST.backend_api+endpoint.get_medication_plan + params.id, {
        method: 'GET',
    });

    RestApiClient.performRequest(request, callback);
}

function postMedicationPlan(med, callback){
    let request = new Request(HOST.backend_api+endpoint.post_medication_plan, {
        method: 'POST',
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(med)
    });

    RestApiClient.performRequest(request, callback);
}

function putMedicationPlan(params, med, callback){
    let request = new Request(HOST.backend_api+endpoint.put_medication_plan,{
        method: 'PUT',
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(med)
    });

    RestApiClient.performRequest(request, callback);
}

function deleteMedicationPlan(med, callback){
    let request = new Request(HOST.backend_api+endpoint.delete_medication_plan,{
        method: 'DELETE',
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(med)
    });

    RestApiClient.performRequest(request, callback);
}

function getMedicationPlanByNumber(nr, med, callback){
    let request = new Request(HOST.backend_api+endpoint.find_medication_plan_by_nr+nr,{
        method: 'GET',
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(med)
    });

    RestApiClient.performRequest(request, callback);
}

export {
    getMedicationPlans,
    getMedicationPlanById,
    postMedicationPlan,
    putMedicationPlan,
    deleteMedicationPlan,
    getMedicationPlanByNumber
}