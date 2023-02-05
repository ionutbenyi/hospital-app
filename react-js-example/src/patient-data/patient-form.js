import React from 'react';
import validate from "./validators/patient-validators";
import TextInput from "./fields/TextInput";
import './fields/fields.css';
import APIResponseErrorMessage from "../commons/errorhandling/api-response-error-message";
import * as API_PATIENTS from "../patient-data/api/patient-api"
import PatientsList from '../doctor-data/patients-list'
import Button from "react-bootstrap/Button";

class PatientForm extends React.Component{

    constructor(props){
        super(props);
        this.toggleForm = this.toggleForm.bind(this);
        
        this.resultedPatients=[];

        this.state = {
            operationStatus: "",
            errorStatus: 0,
            error: null,
            formIsValid: false,
            buttonSelect:'',
            patientToUpdate: null,

            formControls: {
                name: {
                    value: '',
                    placeholder: 'Enter your name',
                    valid: false,
                    touched: false,
                    validationRules: {
                        minLength: 3,
                        isRequired: true
                    }
                },

                email: {
                    value: '',
                    placeholder: 'Your email here',
                    valid: false,
                    touched: false,
                    validationRules: {
                        emailValidator: true,
                        isRequired: true
                    }
                },

                password: {
                    value: '',
                    placeholder: 'Your password here',
                    valid: false,
                    touched: false,
                    validationRules: {
                        passwordValidator: true,
                        isRequired: true
                    }
                }
            }
        };

        this.handleChange = this.handleChange.bind(this);
        this.handleSubmit = this.handleSubmit.bind(this);
        this.reply_click = this.reply_click.bind(this);
        this.updatePatient = this.updatePatient.bind(this);
    }

    toggleForm(){
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
        updatedFormElement.touched = true;
        updatedFormElement.valid = validate(value, updatedFormElement.validationRules);

        console.log("Element: " +  name + " validated: " + updatedFormElement.valid);

        updatedControls[name] = updatedFormElement;

        let formIsValid = true;
        for (let updatedFormElementName in updatedControls) {
            formIsValid = updatedControls[updatedFormElementName].valid && formIsValid;
        }

        this.setState({
            formControls: updatedControls,
            formIsValid: formIsValid
        });

    }

    refr(){
        this.setState({
            patientToUpdate:null,
            errorStatus: 0,
            error: null,
            formIsValid: false,
            buttonSelect:'',
            patientObject:null,

            formControls: {
                name: {
                    value: '',
                    placeholder: 'Enter your name',
                    valid: false,
                    touched: false,
                    validationRules: {
                        minLength: 3,
                        isRequired: true
                    }
                },

                email: {
                    value: '',
                    placeholder: 'Your email here',
                    valid: false,
                    touched: false,
                    validationRules: {
                        emailValidator: true,
                        isRequired: true
                    }
                },

                password: {
                    value: '',
                    placeholder: 'Your password here',
                    valid: false,
                    touched: false,
                    validationRules: {
                        passwordValidator: true,
                        isRequired: true
                    }
                }
            }
        });
    }

    registerPatient(pat){
        return API_PATIENTS.postPatient(pat,(result,status,error) => {
            if(result != null && (status === 200 || status === 201)){
                return "OK";
            } else {
                this.status.errorStatus = status;
                this.error = error;
                return "FAIL";
            }
        });
    }

    reply_click(buttonId){
        this.setState({buttonSelect:buttonId});
    }

    updatePatient(p){
        this.setState({patientToUpdate: p});
    }

    regUpdatePatient(pat){
        let p = this.state.patientToUpdate;
        
        p.name = pat.name;
        p.email = pat.email;
        p.password = pat.password;
        console.log(p);

        return API_PATIENTS.putPatient(p,(result,status,error) => {
            if(result != null && (status === 200 || status === 201)){
                return "OK";
            } else {
                return null;
            }
        });
    }

    handleSubmit(event){
        const name = this.state.buttonSelect;
        let pat = {
            name: this.state.formControls.name.value,
            email: this.state.formControls.email.value,
            password: this.state.formControls.password.value
        };

        if( name === "add-pat")
            this.registerPatient(pat);
        else
            this.regUpdatePatient(pat);

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

    deletePatient(p){
        return API_PATIENTS.deletePatient(p,(result,status,error) => {
            return true;
        });
    }

    componentWillMount(){
        this.fetchPatients();

    }

    render(){
        return (

            <form onSubmit ={this.handleSubmit}>
                <h1>Patient register</h1>

                <p>Name:</p>
                <TextInput name="name"
                    placeholder={this.state.formControls.name.placeholder}
                    value={this.state.formControls.name.value}
                    onChange = {this.handleChange}
                    touched = {this.state.formControls.name.touched}
                    valid = {this.state.formControls.name.valid}
                />
                {this.state.formControls.name.touched && !this.state.formControls.name.valid && 
                    <div className={"error-message"}>*Name must have a valid format</div>}

                <p>Email:</p>
                <TextInput name="email"
                    placeholder={this.state.formControls.email.placeholder}
                    value={this.state.formControls.email.value}
                    onChange = {this.handleChange}
                    touched = {this.state.formControls.email.touched}
                    valid = {this.state.formControls.email.valid}
                />
                {this.state.formControls.email.touched && !this.state.formControls.email.valid && 
                    <div className={"error-message"}>*Email must have a valid format</div>}
            
                <p>Password:</p>
                <TextInput name="password"
                    placeholder={this.state.formControls.password.placeholder}
                    value={this.state.formControls.password.value}
                    onChange = {this.handleChange}
                    touched = {this.state.formControls.password.touched}
                    valid = {this.state.formControls.password.valid}
                />
                {this.state.formControls.password.touched && !this.state.formControls.password.valid && 
                    <div className={"error-message"}>*Password must have a valid format</div>}
            
            <p></p>
            <Button variant="success" id="add-pat"
                    type={"submit"}
                    disabled={!this.state.formIsValid}
                    onClick={e => this.reply_click(e.target.id)}>
                Add
            </Button>

            <Button variant="success" id="upd-pat"
                    type={"submit"}
                    disabled={!this.state.formIsValid}
                    onClick={e => this.reply_click(e.target.id)}>
                Update
            </Button>

            <div className="delete-patient-status">
                <PatientsList pList={this.resultedPatients} onResponseCall={this.deletePatient} messageT={"Delete User"}/>
                <p>{this.state.operationStatus}</p>
            </div>

            <div className="update-patient-status">
                <PatientsList pList={this.resultedPatients} onResponseCall={this.updatePatient} messageT={"Update User"}/>
                <p>{this.state.operationStatus}</p>
            </div>

            {this.state.errorStatus > 0 &&
            <APIResponseErrorMessage errorStatus={this.state.errorStatus} error={this.state.error}/>}
            
            </form>
        );
    }
}

export default PatientForm;