import React from 'react';
import * as API_MEDPLAN from "./api/medplan-api";
import {Card, Col, Row} from 'reactstrap';
import Table from "../commons/tables/table";

const columns = [
    {
        Header:  'Number',
        accessor: 'number',
    },
    {
        Header: 'Patient',
        accessor: 'patient',
    },
    {
        Header: 'Medication',
        accessor: 'medication',
    },
    {
        Header: 'Start',
        accessor: 'start',
    },
    {
        Header: 'End',
        accessor: 'end',
    }

];

const filters = [
    {
        accessor: 'number',
    },
    {
        accessor: 'patient',
    },
    {
        accessor: 'medication',
    },
    {
        accessor: 'start',
    },
    {
        accessor: 'end',
    },
];
class MedplanStatus extends React.Component{

    constructor(props){
        super(props);
        this.tableData = [];
    }

    fetchMedicationPlans(){
        
        return API_MEDPLAN.getMedicationPlans((result,status,err) => {
            console.log("here "+ status);
            if(result != null && status == 200){
                result.forEach(x => {
                    console.log(x.number);
                    this.tableData.push({
                        number: x.number,
                        patient: x.patient.name,
                        medication: x.medication.name,
                        start: x.hourStart,
                        end: x.hourEnd
                    });
                   
                });
                this.forceUpdate();
            } else {
                this.forceUpdate();
            }
        });
    }

    componentDidMount(){
        this.fetchMedicationPlans();
    }

    render(){
        let pageSize = 5;
        return(     
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
        );
    }
    
}

export default MedplanStatus;