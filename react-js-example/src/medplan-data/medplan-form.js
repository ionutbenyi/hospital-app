import React from 'react';
import TextInput from "./fields/TextInput";
import './fields/fields.css';
import Button from "react-bootstrap/Button";
import * as API_MEDPLAN from "../medplan-data/api/medplan-api"
import * as API_PATIENTS from "../patient-data/api/patient-api"
import * as API_MEDICATION from "../medication-data/api/medication-api"
import PatientsList from '../doctor-data/patients-list'

class MedplanForm extends React.Component{

    constructor(props){
        super(props);
        this.state = {
            buttonSelect:'',
            patientM: null,
            medicationM: null,
            formControls: {
                number: {
                    value: '',
                    placeholder: 'Medplan nr'
                },

                hourStart: {
                    value: '',
                    placeholder: 'Start hour'
                },

                hourEnd: {
                    value: '',
                    placeholder: 'End hour'
                }
            }

        }
        this.resultedPatients=[];
        this.resultedMedications=[];
        this.submitForm = this.submitForm.bind(this);
        this.addPatient = this.addPatient.bind(this);
        this.addMedication = this.addMedication.bind(this);
        this.handleSubmit = this.handleSubmit.bind(this);
    }

    submitForm(){
        this.setState({collapseForm: !this.state.collapseForm});
    }

    handleChange = event => {

        const name = event.target.name;
        const value = event.target.value;

        const updatedControls = {
            ...this.state.formControls
        };

        const updatedFormElement = {
            ...updatedControls[name]
        };

        updatedFormElement.value = value;
        updatedControls[name] = updatedFormElement;

        this.setState({
            formControls: updatedControls
        });

    }

    reply_click(buttonId){
        this.setState({buttonSelect:buttonId});
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

    fetchMedications(){
        return API_MEDICATION.getMedication((result,status,err) => {
            
            if(result != null && status == 200){
                result.forEach(x => {
                    this.resultedMedications.push(x);
                });
                this.forceUpdate();
            } else {
                this.state.errorStatus = status;
                this.state.error = err;
                this.forceUpdate();
            }
        });
    }

    addPatient(pat){
        this.setState({patientM: pat});
        
    }

    addMedication(med){
        this.setState({medicationM: med});
        
    }

    componentDidMount(){
        this.fetchPatients();
        this.fetchMedications();
    }

    apiAddMedPlan(mp){
        return API_MEDPLAN.postMedicationPlan(mp,(result,status,error) => {
            if(result != null && (status === 200 || status === 201)){
                this.props.refresh();
            } else {
                this.status.errorStatus = status;
                this.error = error;
                this.props.refresh();
            }
        });
    }

    apiUpdateMedPlan(mp){
        return API_MEDPLAN.getMedicationPlanByNumber(mp.number,(result,status,error) => {
            if(result != null && (status === 200 || status === 201)){
                let self = this;
                return API_MEDPLAN.putMedicationPlan(result, (result1,status1,error1) => {
                    if(result1 != null && (status1 === 200 || status1 === 201)){
                        self.props.refresh();
                    } else {

                        console.log("errrrorrr");
                    };
                });
            } else {
                this.props.refresh();
            }
        });
    }

    handleSubmit(){
        const name = this.state.buttonSelect;
        let mp = {
            number: this.state.formControls.number.value,
            hourStart: this.state.formControls.hourStart.value,
            hourEnd: this.state.formControls.hourEnd.value,
            patient: this.state.patientM,
            medication: this.state.medicationM
        };

        if( name === "add-mp")
            this.apiAddMedPlan(mp);
        else if( name === "upd-mp")
            this.apiUpdateMedPlan(mp);
    }

    render(){
        return(
        <div className="medplan-form-container" >
            <form onSubmit ={this.handleSubmit}>
                <h1>New medication plan</h1>

                <p>Number:</p>
                <TextInput name="number"
                    placeholder={this.state.formControls.number.placeholder}
                    value={this.state.formControls.number.value}
                    onChange = {this.handleChange}
                />

                <p>Start Hour:</p>
                <TextInput name="hourStart"
                    placeholder={this.state.formControls.hourStart.placeholder}
                    value={this.state.formControls.hourStart.value}
                    onChange = {this.handleChange}
                />
            
                <p>End Hour:</p>
                <TextInput name="hourEnd"
                    placeholder={this.state.formControls.hourEnd.placeholder}
                    value={this.state.formControls.hourEnd.value}
                    onChange = {this.handleChange}
                />
            
                { <div className="medplan-adds-patient">
                    <PatientsList pList={this.resultedPatients} onResponseCall={this.addPatient} messageT={"Add Patient"}/>
                </div>}

                <div className="medplan-adds-medication">
                    <PatientsList pList={this.resultedMedications} onResponseCall={this.addMedication} messageT={"Add Medication"}/>
                </div>

                <Button variant="success" id="add-mp"
                        type={"submit"}
                        onClick={e => this.reply_click(e.target.id)}>
                    Add
                </Button>

                <Button variant="success" id="upd-mp"
                        type={"submit"}
                        onClick={e => this.reply_click(e.target.id)}>
                    Update
                </Button>
                
            </form>
        </div>
        );
    }
}

export default MedplanForm;