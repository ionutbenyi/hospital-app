import React from 'react'
import logo from './commons/images/icon.png';

import {
    DropdownItem,
    DropdownMenu,
    DropdownToggle,
    Nav,
    Navbar,
    NavbarBrand,
    NavLink,
    UncontrolledDropdown
} from 'reactstrap';

const textStyle = {
    color: 'white',
    textDecoration: 'none'
};

const NavigationBar = () => (
    <div>
        <Navbar color="dark" light expand="md">
            <NavbarBrand href="/">
                <img src={logo} width={"50"}
                     height={"35"} />
            </NavbarBrand>
            <Nav className="mr-auto" navbar>

                <UncontrolledDropdown nav inNavbar>
                    <DropdownToggle style={textStyle} nav caret>
                       Menu
                    </DropdownToggle>
                    <DropdownMenu right >

                        <DropdownItem>
                            <NavLink href="/doctors">For Doctors</NavLink>
                        </DropdownItem>
                        <DropdownItem>
                            <NavLink href="/patients">For Patients</NavLink>
                        </DropdownItem>
                        <DropdownItem>
                            <NavLink href="/caregivers">For Caregivers</NavLink>
                        </DropdownItem>

                    </DropdownMenu>
                    
                </UncontrolledDropdown>

            </Nav>
        </Navbar>
    </div>
);

export default NavigationBar
