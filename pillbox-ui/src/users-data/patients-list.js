import React from 'react';

import {
    Dropdown
}  from 'react-bootstrap';
import 'bootstrap/dist/css/bootstrap.min.css';
import NavLink from 'react-bootstrap/NavLink';

const textStyle = {
    color: 'white',
    textDecoration: 'none'
};

class PatientsList extends React.Component{

    constructor(props){
        super(props);
    }

    selectPatient(p){
        this.props.onResponseCall(p);
    }

    render(){
        return (

            <Dropdown>
                <Dropdown.Toggle variant="success" id="dropdown-basic">
                    {this.props.messageT}
                </Dropdown.Toggle>

                <Dropdown.Menu>
                    {this.props.pList.map((item,index) =>(
                        <Dropdown.Item >
                            <NavLink onClick={() => this.selectPatient(item)} key={index}>{item.name}</NavLink>
                        </Dropdown.Item>
                    ))}
                </Dropdown.Menu>
            </Dropdown>
        );
    }
}

export default PatientsList