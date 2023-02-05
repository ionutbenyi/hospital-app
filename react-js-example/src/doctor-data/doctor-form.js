import React from 'react';
import validate from "./validators/doctor-validators";
import TextInput from "./fields/TextInput";
import './fields/fields.css';
import APIResponseErrorMessage from "../commons/errorhandling/api-response-error-message";
import * as API_USERS from "./api/doctor-api";
import Button from "react-bootstrap/Button";
import {NavLink} from 'reactstrap';

class DoctorForm extends React.Component{

    constructor(props){
        super(props);
        this.toggleForm = this.toggleForm.bind(this);

        this.state = {
            errorStatus: 0,
            error: null,
            formIsValid: false,

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

    registerDoc(doc){
        return API_USERS.postDoctor(doc,(result,status,error) => {
            if(result != null && (status === 200 || status === 201)){
                console.log("Successfully inserted doctor with id: " + result);
                this.props.refresh();
            } else {
                this.status.errorStatus = status;
                this.error = error;
            }
        });
    }

    handleSubmit(){
        //aici e ingropat cainele
        let doctor ={
            name: this.state.formControls.name.value,
            email: this.state.formControls.email.value,
            password: this.state.formControls.password.value
        };

        this.registerDoc(doctor);
    }

    render(){
        return (

            <form onSubmit ={this.handleSubmit}>
                <h1>Doctor register</h1>

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
            <Button variant="success"
                    type={"submit"}
                    disabled={!this.state.formIsValid}>
                Submit
            </Button>

            <NavLink href="/doctors/login">Doctors login</NavLink>

            {this.state.errorStatus > 0 &&
            <APIResponseErrorMessage errorStatus={this.state.errorStatus} error={this.state.error}/>}
            
            </form>
        );
    }
}

export default DoctorForm;