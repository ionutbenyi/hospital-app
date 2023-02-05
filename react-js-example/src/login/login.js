import React from 'react';
import * as API_DOCTORS from "../doctor-data/api/doctor-api";
import * as API_CAREGIVERS from "../caregiver-data/api/caregiver-api";
import * as API_PATIENTS from "../patient-data/api/patient-api";
import TextInput from "./fields/TextInput";
import './fields/fields.css';
import Button from "react-bootstrap/Button";
import validate from "../doctor-data/validators/doctor-validators";
import {withRouter} from 'react-router'

class Login extends React.Component {

    constructor(props){
        super(props);
        this.toggleLogin = this.toggleLogin.bind(this);
        this.state = {
            errorStatus:"",
            error:"",
            collapseForm: true,
            formControls: {
                email: {
                    value: '',
                    placeholder: 'Enter your email',
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
        }

        this.handleChange = this.handleChange.bind(this);
        this.handleSubmit = this.handleSubmit.bind(this);
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

    toggleLogin(){
        this.setState({collapseForm: !this.state.collapseForm});
    }

    loginUser(logPair){
        
        if(this.props.urole === "Doctor"){
            return API_DOCTORS.loginDoctor(logPair,(result,status,error) =>  {
                if(result != null && (status == 200 || status == 201)){
                    let url = "/doctor/"+result.id;
                    this.props.history.push(url);
                } else {
                    console.log("not okay");
                    
                    this.props.history.push('/error');
                }
            });
        }else if(this.props.urole === "Caregiver"){
            return API_CAREGIVERS.loginCaregiver(logPair,(result,status,error) =>  {
                if(result != null && (status == 200 || status == 201)){
                    let url = "/caregiver/"+result.id;
                    this.props.history.push(url);
                } else {
                    this.status.errorStatus = status;
                    this.error = error;
                    this.props.history.push('/error');
                }
            });
        }else{
            return API_PATIENTS.loginPatient(logPair,(result,status,error) =>  {
                if(result != null && (status == 200 || status == 201)){
                    let url = "/patient/"+result.id;
                    this.props.history.push(url);
                    
                } else {
                    this.status.errorStatus = status;
                    this.error = error;
                    this.props.history.push('/error');
                }
            });
        }

        
    }

    handleSubmit(event){
        let loginPair={
            email: this.state.formControls.email.value,
            password: this.state.formControls.password.value
        }
        
        let mjsx = this.loginUser(loginPair);
        event.preventDefault();
        return mjsx;
    }

    render(){
        console.log("rendering");
        return(
            <div>
                <form onSubmit ={this.handleSubmit}>
                    <h1>{this.props.urole} log in</h1>
                    <p>Email:</p>
                    <TextInput name="email"
                        placeholder={this.state.formControls.email.placeholder}
                        value={this.state.formControls.email.value}
                        onChange = {this.handleChange}
                        touched = {this.state.formControls.email.touched}
                        valid = {this.state.formControls.email.valid}
                    />

                    <p>Password:</p>
                    <TextInput name="password"
                        placeholder={this.state.formControls.password.placeholder}
                        value={this.state.formControls.password.value}
                        onChange = {this.handleChange}
                        touched = {this.state.formControls.password.touched}
                        valid = {this.state.formControls.password.valid}
                    />  

                    <p></p>
                    <Button variant="success"
                            type={"submit"}
                            disabled={!this.state.formIsValid}>
                        Log in 
                    </Button>

                </form>
            </div>
        );
    }
}

export default withRouter(Login);