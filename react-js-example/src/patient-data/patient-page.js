import React from 'react';
import * as API_PATIENT from "../patient-data/api/patient-api"
import {Card, Col, Row} from 'reactstrap';

class PatientPage extends React.Component {

    constructor(props){
        super(props);
        this.state = {
            errorStatus:"",
            error:"",
            message: "",
            patient: null
        }
    }

    fetchPatient(id){

        return API_PATIENT.getPatientById(id,(result,status,err) =>
        {
            if(result != null && status == 200){
                this.setState({patient:result, message:"- Welcome, "+result.name+"!"});
                this.forceUpdate();
            }else{
                this.state.errorStatus = status;
                this.state.error = err;
                this.forceUpdate();
            }
        });
    }

    componentWillMount(){
        let i = this.props.match.params;
        if(i!=null){
           this.fetchPatient(i);
        }
    }

    render(){
        return(
            <Row >
                    <h1 className="title-row">Patient dashboard {this.state.message} </h1>
                </Row>
        );
    }
}

export default PatientPage;