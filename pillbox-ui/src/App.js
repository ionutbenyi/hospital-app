import React from 'react';
import {BrowserRouter as Router, Route, Switch} from 'react-router-dom';

import './App.css';
import Users from './users-data/users';
import Pillbox from './medplan-data/pillbox';

class App extends React.Component {

  render() {

      return (
          <div >
          <Router>
              <div>
                  <Switch>

                      <Route
                          exact
                          path='/'
                          render={(props) => <Users {...props}/>}
                      />

                      <Route
                          exact
                          path="/medplan/:uid"
                          render = {props => 
                          <Pillbox {...props} 
                          /> } />
                      />

                  </Switch>
              </div>
          </Router>
          </div>
      )
  };
}

export default App
