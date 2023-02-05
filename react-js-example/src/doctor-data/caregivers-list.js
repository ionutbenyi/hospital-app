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

class CaregiversList extends React.Component{

    constructor(props){
        super(props);
    }

    selectCaregiver(p){
        this.props.onResponseCall(p);
    }

    render(){
        return (

            <UncontrolledDropdown className="caregivers-dropdown-container" nav inNavbar>
                        <DropdownToggle style={textStyle}>
                            {this.props.messageT}
                        </DropdownToggle>
                        <DropdownMenu className="dropdown-caregivers-menu" >
                            {this.props.pList.map((item,index) =>(
                                <DropdownItem>
                                    <NavLink onClick={() => this.selectCaregiver(item)} key={index}>{item.name}</NavLink>
                                </DropdownItem>
                            ))}
                        </DropdownMenu>
                        
                </UncontrolledDropdown>
        );
    }
}

export default CaregiversList