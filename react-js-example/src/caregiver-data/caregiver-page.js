import React from 'react';
import PatientsList from '../doctor-data/patients-list'
import {Card, Col, Row} from 'reactstrap';
import Table from "../commons/tables/table";
import * as API_CAREGIVER from "../caregiver-data/api/caregiver-api"
import * as API_PATIENT from "../patient-data/api/patient-api"

import * as Stomp from "stompjs"
import * as SockJS from "sockjs-client"

const columns = [
    {
        Header:  'Name',
        accessor: 'name',
    },
    {
        Header: 'Email',
        accessor: 'email',
    },

];

const filters = [
    {
        accessor: 'name',
    },
    {
        accessor: 'email',
    }
];

class CaregiverPage extends React.Component {

    constructor(props){
        super(props);
        this.patients = [];
        this.tabledata=[];
        
        this.caregiver = null;
        this.state ={
            errorStatus:"",
            error:"",
            message: "",
            alertMessage: ""
        }
        this.addPatient = this.addPatient.bind(this);
        this.handleMsg = this.handleMsg.bind(this);
    }

    fetchCaregiver(id){

        return API_CAREGIVER.getCaregiverById(id,(result,status,err) =>
        {
            if(result != null && status == 200){
                this.setState({message:"- Welcome, "+result.name+"!", });
                
                result.patientsList.forEach((x) => {
                    this.tabledata.push({id: x.id,
                    name: x.name,
                    email: x.email});
                });
                this.forceUpdate();
            }else{
                this.state.errorStatus = status;
                this.state.error = err;
                this.forceUpdate();
            }
        });
    }

    fetchPatients(){
        return API_PATIENT.getPatients((result,status,err) =>{
            if(result != null && status == 200){
                this.patients = result;
                this.forceUpdate();
            }else{
                this.state.errorStatus = status;
                this.state.error = err;
                this.forceUpdate();
            }
        });
    }

    handleMsg(msg){
        console.log("received: msg +"+msg);
    }

    componentDidMount(){
        let i = this.props.match.params;
        if(i!=null){
           this.fetchCaregiver(i);

        }
        this.fetchPatients();
        
        var socket = new SockJS('http://localhost:8080/status');
        var stompClient = Stomp.over(socket);
        var self=this;
        stompClient.connect({}, function (frame) {
            
            console.log('Connected: ' + socket);
            if(stompClient)
                stompClient.subscribe('/topic/app', function (message) {
                    var msgDict = JSON.parse(message.body);
                    console.log("received: msg +"+msgDict);
                    console.log(msgDict["patientId"] +" - "+msgDict["message"]);
                    self.setState({alertMessage:msgDict["patientId"] +"-"+ msgDict["message"]});
                });
        });

    }

    addPatient(p){
        return API_CAREGIVER.addPatientCaregiver(p,this.props.match.params,(result,status,err) =>{
            if(result != null && status == 200){
                this.forceUpdate();
            }else{
                this.state.errorStatus = status;
                this.state.error = err;
                this.forceUpdate();
            }
        });
    }

    render(){
       
        return(
            <div>
                <Row >
                    <h1 class="title-row">Caregiver dashboard {this.state.message} </h1>
                </Row>

                <Row>
                    <div className="caregiver-adds-patient">
                        <PatientsList pList={this.patients} onResponseCall={this.addPatient} messageT={"Add Patient"}/>
                    </div>
                </Row>

                <Row>
                    <Col>
                        <ul>
                            {this.tabledata.map((item) => (
                            <li key={item.id} >{item.name},{item.email}</li>
                            ))}
                        </ul>
                    </Col>
                </Row>
                <Row>
                    <ul>
                        {this.state.alertMessage}
                    </ul>
                </Row>
                
            </div>
        );
    }
}

export default CaregiverPage;