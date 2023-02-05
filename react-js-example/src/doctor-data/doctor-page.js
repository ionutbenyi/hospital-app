import React from 'react';

import './fields/fields.css';
import PatientForm from "../patient-data/patient-form"
import {Card, Col, Row} from 'reactstrap';
import CaregiverForm from '../caregiver-data/caregiver-form';
import MedicationForm from '../medication-data/medication-form';
import * as API_PATIENTS from "../patient-data/api/patient-api"
import * as API_DOCTOR from "../doctor-data/api/doctor-api"
import {NavLink} from 'reactstrap';
import {DropdownItem} from 'reactstrap';
import Button from "react-bootstrap/Button";
import PatientsList from './patients-list'

class DoctorPage extends React.Component {

    constructor(props){
        super(props);
        this.resultedPatients = [];

        this.state ={
            errorStatus:"",
            error:"",
            message: "",
            doctor: null
        }

        this.selectPatient = this.selectPatient.bind(this);
    }

    fetchPatients(){
        return API_PATIENTS.getPatients((result,status,err) => {
            
            if(result != null && status == 200){
                result.forEach(x => {
                    this.resultedPatients.push(x);
                });
                this.forceUpdate();
            } else {
                this.state.errorStatus = status;
                this.state.error = err;
                this.forceUpdate();
            }
        });
    }

    fetchDoctor(id){

        return API_DOCTOR.getDoctorById(id,(result,status,err) =>
        {
            if(result != null && status == 200){
                this.setState({doctor:result, message:"- Welcome, Dr. "+result.name+"!"});
                this.forceUpdate();
            }else{
                this.state.errorStatus = status;
                this.state.error = err;
                this.forceUpdate();
            }
        });
    }

    componentWillMount(){
        this.fetchPatients();
        let i = this.props.match.params;
        if(i!=null){
           this.fetchDoctor(i);
        }
    }

    selectPatient(p){
        let url = "/patientStatus/"+p.id;
        this.props.history.push(url);
    }

    render(){
        return(
            <div>
                <Row >
                    <h1 class="title-row">Doctor dashboard {this.state.message} </h1>
                </Row>
                <Row className="register-section">
                    <Col>
                        <Card body>
                            
                            <div>
                                <PatientForm refresh = {this.refresh} >
                                </PatientForm>
                            </div>
                            
                        </Card>
                    </Col>

                    <Col>
                        <Card body>
                            <div>
                                <CaregiverForm registerCaregiver = {this.refresh} >
                                </CaregiverForm>
                            </div>
                        </Card>
                    </Col>

                    <Col>
                        <Card className="med-form" body>
                            <div>
                                <MedicationForm  registerMedication = {this.refresh} unregisterMedication = {this.refresh}>
                                </MedicationForm>
                            </div>
                            
                        </Card>
                    </Col>
                </Row>

                <Row>
                    
                    <Button className="adm-medpl-button" href="/plans">Administer medication plan</Button>
                    { <div className="patient-status-selector">
                        <PatientsList pList={this.resultedPatients} onResponseCall={this.selectPatient} messageT={"Patient status"}/>
                    </div>}
                </Row>
                
            </div>
        );
    }
}

export default DoctorPage;