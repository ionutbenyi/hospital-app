import React from 'react';

class AccessDenied extends React.Component {

    constructor(props){
        super(props);
    }

    render(){
        return (
            <div class="error-access-msg">
                Access Denied!
            </div>
        );
    }
}

export default AccessDenied;