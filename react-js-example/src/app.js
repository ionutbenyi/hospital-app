import React from 'react'
import {BrowserRouter as Router, Route, Switch} from 'react-router-dom'
import NavigationBar from './navigation-bar'
import Home from './home/home';
import Doctors from './doctor-data/doctors'
import Caregivers from './caregiver-data/caregivers'
import Patients from './patient-data/patients'
import Login from './login/login'
import DoctorPage from './doctor-data/doctor-page'
import CaregiverPage from './caregiver-data/caregiver-page'
import PatientPage from './patient-data/patient-page'
import ErrorPage from './commons/errorhandling/error-page';
import styles from './commons/styles/project-style.css';
import AccessDenied from './access-denied'
import MedicationPlanPage from './medplan-data/medplan-page'
import DoctorStatusPage from './doctor-data/doctor-status-page';

let enums = require('./commons/constants/enums');

class App extends React.Component {

    render() {

        return (
            <div className={styles.back}>
            <Router>
                <div>
                    <NavigationBar />
                    <Switch>

                        <Route
                            exact
                            path='/'
                            render={() => <Home/>}
                        />

                        <Route
                            exact
                            path='/doctors'
                            render={() => <Doctors/>}
                        />

                        <Route
                            exact
                            path='/doctors/login'
                            render={() => <Login urole={"Doctor"}/>}
                        />

                        <Route
                            exact
                            path="/doctor/:id"
                            render = {props => 
                            <DoctorPage {...props} 
                            /> } />
                        />

                        <Route
                            exact
                            path='/caregivers'
                            render={() => <Caregivers/>}
                        />

                        <Route
                            exact
                            path='/caregivers/login'
                            render={() => <Login urole={"Caregiver"}/>}
                        />

                        <Route
                            exact
                            path="/caregiver/:id"
                            render = {props => 
                            <CaregiverPage {...props} 
                            /> } />
                        />

                        <Route
                            exact
                            path='/patients'
                            render={() => <Patients/>}
                        />

                        <Route
                            exact
                            path='/patients/login'
                            render={() => <Login urole={"Patient"}/>}
                        />

                        <Route
                            exact
                            path="/patient/:id"
                            render = {props => 
                            <PatientPage {...props} 
                            /> } />
                        />

                        <Route
                            exact
                            path="/patientStatus/:id"
                            render = {props => 
                            <DoctorStatusPage {...props} 
                            /> } />
                        />

                        <Route
                            exact
                            path='/error'>
                            render={() => <AccessDenied />}
                        </Route>

                        {/*Error*/}
                        <Route
                            exact
                            path='/error'
                            render={() => <ErrorPage/>}
                        />

                        <Route
                            exact
                            path="/plans"
                            render = {props => 
                            <MedicationPlanPage {...props} 
                            /> } />
                        />

                        <Route render={() =><ErrorPage/>} />
                    </Switch>
                </div>
            </Router>
            </div>
        )
    };
}

export default App
