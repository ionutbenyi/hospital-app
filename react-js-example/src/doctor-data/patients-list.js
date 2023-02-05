import React from 'react';

import {
    DropdownItem,
    DropdownMenu,
    DropdownToggle,
    UncontrolledDropdown
} from 'reactstrap';
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

            <UncontrolledDropdown className="patients-dropdown-container" nav inNavbar>
                        <DropdownToggle style={textStyle}>
                            {this.props.messageT}
                        </DropdownToggle>
                        <DropdownMenu className="dropdown-patients-menu" >
                            {this.props.pList.map((item,index) =>(
                                <DropdownItem>
                                    <NavLink onClick={() => this.selectPatient(item)} key={index}>{item.name}</NavLink>
                                </DropdownItem>
                            ))}
                        </DropdownMenu>
                        
                </UncontrolledDropdown>
        );
    }
}

export default PatientsList