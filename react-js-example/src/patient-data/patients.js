import React from 'react';
import {NavLink} from 'reactstrap';
class Patients extends React.Component {

    constructor(props){
        super(props);
    }

    render(){
        return (
            <div>
                <h1>Patients Logic here</h1>
                <NavLink href="/patients/login">Patients login</NavLink>
            </div>
        );
    };
}

export default Patients;