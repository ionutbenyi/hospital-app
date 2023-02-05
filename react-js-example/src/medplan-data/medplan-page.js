import React from 'react';
import MedplanForm from '../medplan-data/medplan-form';
import MedplanStatus from '../medplan-data/medplan-status';

class MedicationPlanPage extends React.Component{

    constructor(props){
        super(props);
    }

    render(){
        let pageSize = 5;
        return(
            <div>
                <MedplanForm />     
                <MedplanStatus />
            </div>
        );
    }
}

export default MedicationPlanPage;