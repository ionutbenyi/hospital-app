import React from 'react';
import {Card, Col, Row} from 'reactstrap';
import Table from "../commons/tables/table"
import DoctorForm from "./doctor-form";

import * as API_USERS from "./api/doctor-api"

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

class Doctors extends React.Component {

    constructor(props){
        super(props);
        this.toggleForm = this.toggleForm.bind(this);
        this.state = {
            collapseForm: true,
            loadPage: false,
            errorStatus: 0,
            error: null
        };

        this.tableData = [];
    }

    toggleForm(){
        this.setState({collapseForm: !this.state.collapseForm});
    }

    componentDidMount(){
        this.fetchDoctors();
    }

    fetchDoctors(){
        return API_USERS.getDoctors((result,status,err) => {
            console.log("fetch docs: result="+result);
            if(result != null && status == 200){
                result.forEach(x => {
                    this.tableData.push({
                        name: x.name,
                        email: x.email,
                    });
                });
                this.forceUpdate();
            } else {
                this.state.errorStatus = status;
                this.state.error = err;
                this.forceUpdate();
            }
        });
    }

    refresh(){
        this.forceUpdate()
    }

    render(){
        let pageSize = 5;
        return (
            <div>
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
                    <Col>
                        <Card body>
                            <div>
                                <DoctorForm registerDoc = {this.refresh} >
                                </DoctorForm>
                            </div>
                        </Card>
                    </Col>
                </Row>
            </div>
        );
    };
}

export default Doctors;