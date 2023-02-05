import React from 'react';
import TextInput from "./fields/TextInput";
import './fields/fields.css';
import * as API_MED from "./api/medication-api";
import Button from "react-bootstrap/Button";
import {Card, Col, Row} from 'reactstrap';

class MedicationForm extends React.Component{

    constructor(props){
        super(props);
        this.toggleForm = this.toggleForm.bind(this);

        
        this.state = {
            errorStatus: 0,
            error: null,
            buttonSelect:'',
            formControls: {
                number: {
                    value: '',
                    placeholder: 'Name',
                    touched: false
                },

                sideEff: {
                    value: '',
                    placeholder: 'Side effects',
                    touched: false
                }
            }
        };
        this.handleChange = this.handleChange.bind(this);
        this.handleSubmit = this.handleSubmit.bind(this);
    }

    toggleForm(){
        this.setState({collapseForm: !this.state.collapseForm});
    }

    registerMedication(med){
        return API_MED.postMedication(med,(result,status,error) => {
            if(result != null && (status === 200 || status === 201)){
                //this.props.refresh();
                this.setState({
                    buttonSelect:'',
                    formControls: {
                    number: {
                        value: '',
                        placeholder: 'Name',
                        touched: false
                    },

                    sideEff: {
                        value: '',
                        placeholder: 'Side effects',
                        touched: false
                    }
                    }
                });
            } else {
                this.status.errorStatus = status;
                this.error = error;
            }
        });
    }

    unregisterMedication(med){
        return API_MED.deleteMedication(med,(result,status,error) => {
            if(result != null && (status === 200 || status === 201)){
                this.setState({
                    buttonSelect:'',
                    formControls: {
                    number: {
                        value: '',
                        placeholder: 'Name',
                        touched: false
                    },

                    sideEff: {
                        value: '',
                        placeholder: 'Side effects',
                        touched: false
                    }
                    }
                });
            } else {
                this.status.errorStatus = status;
                this.error = error;
            }
        });
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

        updatedControls[name] = updatedFormElement;

        this.setState({
            formControls: updatedControls
        });

    }

    reply_click(buttonId){
        this.setState({buttonSelect:buttonId});
    }

    handleSubmit(event){

        const name = this.state.buttonSelect;

        let med ={
            name: this.state.formControls.number.value,
            sideEffects: this.state.formControls.sideEff.value,
        };

        console.log(name);

        if(name === "del-med"){
            this.unregisterMedication(med);
        }else{
            this.registerMedication(med);
        }

    }

    render(){
        return (

            <form onSubmit ={this.handleSubmit}>
                <h1>Medication register</h1>

                <p>Name:</p>
                <TextInput name="number"
                    placeholder={this.state.formControls.number.placeholder}
                    value={this.state.formControls.number.value}
                    onChange = {this.handleChange}
                    touched = {this.state.formControls.number.touched}
                
                />
                <p>Side Effects:</p>
                <TextInput name="sideEff"
                    placeholder={this.state.formControls.sideEff.placeholder}
                    value={this.state.formControls.sideEff.value}
                    onChange = {this.handleChange}
                    touched = {this.state.formControls.sideEff.touched}
                   
                />
            
            <p></p>
            <Row>
                <Button variant="success" className = "add-med-button" id="add-med"
                        type={"submit"} onClick={e => this.reply_click(e.target.id)}>
                    Add medication
                </Button>
                <Button variant="success" className = "del-med-button" id="del-med"
                        type={"submit"} onClick={e => this.reply_click(e.target.id)}>
                    Delete medication
                </Button>
            </Row>
            
            </form>
        );
    }
}

export default MedicationForm;