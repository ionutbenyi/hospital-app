import React from 'react';
import * as API_MED from "./api/medplan-api"
import {Card, Col, Row} from 'reactstrap';
import Button from "react-bootstrap/Button";
import './fields/fields.css';

class Pillbox extends React.Component {

    constructor(props){
        super(props);
        this.medicines = [];
        this.state = {
            errorStatus: 0,
            error: null,
            count: 1,
            hour: 0,
        };
        this.reply_click = this.reply_click.bind(this);
        this.findEndingMedicines = this.findEndingMedicines.bind(this);
        this.findStartingMedicines = this.findStartingMedicines.bind(this);
    }

    fetchMedicines(id){

        return API_MED.getMedicines(id,(result,status,err) =>
        {
            if(result != null && status == 200){
                result.forEach(x => {
                    this.medicines.push({
                        name: x.name,
                        startHour: x.startHour,
                        endHour: x.endHour,
                        taken: false
                    });
                });
                this.forceUpdate();
            }else{
                this.state.errorStatus = status;
                this.state.error = err;
                this.forceUpdate();
            }
        });
    }

    takeMedicine(m){
        return API_MED.sendTakenConfirm(this.props.match.params.uid,m.name,(result,status,err) =>
        {
            if(result != null && status == 200){
                
                console.log("good");
                this.forceUpdate();
            }else{
                this.state.errorStatus = status;
                this.state.error = err;
                this.forceUpdate();
            }
        });
    }

    notTakeMedicine(m){
        return API_MED.sendNotTakenConfirm(this.props.match.params.uid,m.name,(result,status,err) =>
        {
            if(result != null && status == 200){
                console.log("good");
                this.forceUpdate();
            }else{
                this.state.errorStatus = status;
                this.state.error = err;
                this.forceUpdate();
            }
        });
    }

    /*timer */

    findEndingMedicines(hour){
        for(let i=0;i<this.medicines.length;i++){
            if(this.medicines[i].endHour <= hour){
                var button = document.getElementById(this.medicines[i].name);
                if(!button.disabled){
                    button.disabled = true;
                    console.log("End all - "+this.medicines[i].taken);
                    
                    if(!this.medicines[i].taken)
                        this.notTakeMedicine(this.medicines[i]);
                    
                    this.medicines[i].taken = false;
                } 
            }   
        }
    }

    findStartingMedicines(hour){
        for(let i=0;i<this.medicines.length;i++){
            if(this.medicines[i].startHour <= hour && hour < this.medicines[i].endHour){
                var button = document.getElementById(this.medicines[i].name);
                
                if(button.disabled){
                    button.disabled = false;
                } 
            }   
        }
    }

    tick () {
        this.findStartingMedicines(this.state.hour);
        this.findEndingMedicines(this.state.hour);
        if(this.state.count < 6)
            this.setState({count: (this.state.count + 1)});
        else{
            
            if(this.state.hour < 23)
                this.setState({hour: (this.state.hour + 1), count: 0});
            else{
                this.setState({hour: 0, count: 0});
            } 
        }
    }
    
    startTimer () {
        clearInterval(this.timer)
        this.timer = setInterval(this.tick.bind(this), 300)
    }

    componentDidMount(){
        let i = this.props.match.params;
        if(i!=null){
           this.fetchMedicines(i);
        }

        console.log(this.medicines);
        this.startTimer();
    }

    reply_click(idB, item){
        
        this.takeMedicine(item);
        item.taken=true;
        var button = document.getElementById(idB);
        button.disabled = true;
    }

    componentWillUnmount () {
        clearInterval(this.timer)
    }

    render(){
        return (
            <div>
                <Row className="medication-row">
                    {this.medicines.map((item,index) =>(
                        <Card className="medication-card">
                            <h2>{item.name}</h2>
                            <div className="medication-details">
                                <div>Start hour: {item.startHour}</div>
                                <div>End hour: {item.endHour}</div>
                            </div>
                            <Button className="take-it-button" id={item.name}
                            onClick={(e) => this.reply_click(e.target.id, item)} 
                            key={index}>Take it</Button>
                        </Card>
                    ))}
                </Row>
                <Row>
                    <Card className="timer-container">
                        {this.state.hour}:{this.state.count}
                    </Card>
                </Row>
            </div>
        );
    }
}

export default Pillbox;