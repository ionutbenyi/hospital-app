

class LogBar extends React.Component{

    constructor(props){
        super(props);
        this.state = {
            errorStatus: 0,
            error: null,
            formControlls: {
                email: {
                    value: '',
                    placeholder: 'Your email here',
                    valid: false,
                    touched: false,
                    validationRules: {
                        emailValidator: true,
                        isRequired: true
                    }
                },
    
                password: {
                    value: '',
                    placeholder: 'Your password here',
                    valid: false,
                    touched: false,
                    validationRules: {
                        passwordValidator: true,
                        isRequired: true
                    }
                }
            }
        }

    }


}

<Button variant="logged"
                            type={"submit"}> 
                    Log in
                    </Button>