import React from 'react';
import * as API_PATIENT from "../patient-data/api/patient-api"
import * as API_DOCTOR from "./api/doctor-api"
import Chart from "react-google-charts";
import {Card, Col, Row} from 'reactstrap';
import Table from "../commons/tables/table";
import TextInput from "./fields/TextInput";
import PatientsList from './patients-list'
import * as API_CAREGIVERS from "../caregiver-data/api/caregiver-api"
import Button from "react-bootstrap/Button";

const columns = [
    {
        Header:  'Record ID',
        accessor: 'recordId',
    },
    {
        Header:  'Name',
        accessor: 'name',
    },
    {
        Header: 'Taken',
        accessor: 'taken',
    },
    

];

const filters = [
    {
        accessor: 'recordId',
    },
    {
        accessor: 'name',
    },
    {
        accessor: 'taken',
    },
    
];

class DoctorStatusPage extends React.Component{

    constructor(props){
        super(props);
        this.tableData = [];
        this.caregivers = [];
        this.data = [
            ["Activity", "Hours", { role: "style" }],
        ];

        this.state = {
            patientName:"",
            formControls: {
                message: {
                    value: ''
                }
            },
            cg: null
        }
        this.handleSubmit = this.handleSubmit.bind(this);
        this.handleChange = this.handleChange.bind(this);
        this.setCaregiver = this.setCaregiver.bind(this);
    }

    fetchPatient(id){

        return API_PATIENT.getPatientById(id,(result,status,err) =>
        {
            if(result != null && status == 200){
                this.setState({patientName: result.name});
                this.forceUpdate();
            }else{
                this.state.errorStatus = status;
                this.state.error = err;
                this.forceUpdate();
            }
        });
    }

    fetchCaregivers(){
        return API_CAREGIVERS.getCaregivers((result,status,err) => {
            
            if(result != null && status == 200){
                result.forEach(x => {
                    this.caregivers.push(x);
                });
            } else {
                this.state.errorStatus = status;
                this.state.error = err;
            }
        });
    }

    fetchPatientmedHistory(id){
        return API_DOCTOR.getPatientMedHistory(id,(result,status,err) =>
        {
            if(result != null && status == 200){
                result.forEach(x => {
                    this.tableData.push({
                        name: x.medicationName,
                        taken: x.taken.toString(),
                        recordId: x.id
                    });
                });
                this.forceUpdate();
            }else{
                this.state.errorStatus = status;
                this.state.error = err;
                this.forceUpdate();
            }
        });
    }

    fetchPatientActivity(id){
        return API_DOCTOR.getPatientActivity(id,(result,status,err) =>
        {
            if(result != null && status == 200){
                result.forEach(x => {
                    if(x.ok)
                        this.data.push([x.activity, x.duration, "blue"]);
                    else this.data.push([x.activity, x.duration, "red"]);
                });
                this.forceUpdate();
            }else{
                this.state.errorStatus = status;
                this.state.error = err;
                this.forceUpdate();
            }
        });
    }



    componentDidMount(){
        let i = this.props.match.params;
        this.fetchPatient(i);
        this.fetchCaregivers();
        this.fetchPatientmedHistory(i.id);
        this.fetchPatientActivity(i.id);
    }

    componentWillUnmount () {
        
        this.data = [
            ["Activity", "Hours", { role: "style" }],
        ];
        this.cg = null;
    }

    setCaregiver(p){
        this.setState({cg: p});
       console.log(p);
       console.log(this.state.formControls.message.value);
    }

    addRecommendation(rec){
        return API_DOCTOR.postRecommendation(rec,(result,status,error) => {
            if(result != null && (status === 200 || status === 201)){
               // this.forceUpdate();
            } else {
                this.status.errorStatus = status;
                this.error = error;
            }
        });
    }

    handleSubmit(){
        let rec ={
            message: this.state.formControls.message.value,
            caregiverId: this.state.cg.id
        };

        this.addRecommendation(rec);
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
            formControls: updatedControls,
           
        });

    }

    render(){
        const pageSize=5;
        return(
            <div>
                <h1>Status of patient {this.state.patientName}</h1>
                <Chart
                    chartType="ColumnChart"
                    width="100%"
                    height="400px"
                    data={this.data}
                />

                <Row>
                    <Col>
                        <Card body>
                            <Table 
                                data = {this.tableData}
                                columns = {columns}
                                search = {filters}
                                pageSize = {pageSize}
                            />
                        </Card>
                    </Col>
                </Row>
                <Row>
                    <Card className = "recommendation">
                        <form onSubmit ={this.handleSubmit}>
                            <h1>Recommendation</h1>
                            <p>Message:</p>
                            <TextInput name="message"
                                value={this.state.formControls.message.value}
                                onChange = {this.handleChange}
                            />
                            <div className="">
                                <PatientsList pList={this.caregivers} onResponseCall={this.setCaregiver} messageT={"Select Caregiver"}/>
                            </div>
                            <Button variant="success" id="add-cg"
                                type={"submit"}>
                                Recommend
                            </Button>
                        </form>
                    </Card>
                </Row>
            </div>
        );
    }
}

export default DoctorStatusPage;