import React from 'react';
import {Card, Col, Row} from 'reactstrap';
import Table from "../commons/tables/table";
import {NavLink} from 'reactstrap';

import * as API_USERS from "./api/caregiver-api"

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

class Caregivers extends React.Component {

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
        this.fetchCaregivers();
    }

    fetchCaregivers(){
        return API_USERS.getCaregivers((result,status,err) => {
          
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
        let pageSize =5;
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
                               
                                <NavLink href="/caregivers/login">Caregivers login</NavLink>
                            </div>
                        </Card>
                    </Col>
                </Row>
            </div>
        );
    };
}

export default Caregivers;