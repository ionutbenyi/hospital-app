import React from 'react';
import * as API_USERS from "./api/users-api"
import PatientsList from "./patients-list"
import './fields/fields.css';

class Users extends React.Component {

    constructor(props){
        super(props);
        this.patients = [];
        this.state = {
            errorStatus: 0,
            error: null
        };
        this.selectPatient = this.selectPatient.bind(this);
    }

    selectPatient(p){
        let url = "/medplan/"+p.id;
        this.props.history.push(url);
    }

    fetchUsers(){
        return API_USERS.getPatients((result,status,err) => {
           
            if(result != null && status == 200){
                result.forEach(x => {
                    this.patients.push({
                        name: x.name,
                        id: x.id,
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

    componentDidMount(){
        this.fetchUsers();
        console.log(this.patients);
    }

    render(){
        return (
            <div>
                <h1>Welcome, Users!</h1>
                { <div >
                    <PatientsList pList={this.patients} onResponseCall={this.selectPatient} messageT={"Add Patient"}/>
                </div>}
            </div>
        );
    }
}

export default Users;