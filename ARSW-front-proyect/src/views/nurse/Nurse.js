import React, { Component } from 'react'
import { Grid, Button } from '@material-ui/core'
import cookie from 'react-cookies';
import { Route, Switch } from 'react-router-dom';
import NAssistant from './N_Assistant';
import NPatient from './N_Patient';
import { Client } from '@stomp/stompjs';

export default class Nurses extends Component {

    state = {
        serverTime: null,
    }
    constructor(props) {
        super(props);
        this.state = { 
            
        }
        this.logout = this.logout.bind(this)
    }
    
      clickHandler = () => {
        this.client.publish({destination: '/app/greetings', body: 'Hello world'});
      }

    logout(){
        cookie.remove('userToken',{path:'/'})
        console.log(cookie.load('userToken'))
        this.props.history.push("/")
    }
    componentDidMount() {
        console.log('Component did mount');
        // The compat mode syntax is totally different, converting to v5 syntax
        // Client is imported from '@stomp/stompjs'
        this.client = new Client();
    
        console.log(this.client);
    
        console.log('client');
    
        this.client.configure({
          brokerURL: 'ws://localhost:8081/stomp',
          onConnect: () => {
            console.log('onConnect');
            this.client.subscribe('/topic/greetings', message => {
              alert(message.body);
            });
          },
          // Helps during debugging, remove in production
          debug: (str) => {
            console.log(new Date(), str);
          }
        });
    }

    
    
    render() {
        return (
            <div>
                <Grid container style={{paddingLeft: 5, paddingRight: 5}}>
                    <Grid item xs={4}>
                        <Grid container >
                            <Grid item xs={1}></Grid>
                            <Grid item xs={3}>
                                <Button
                                    type="submit"
                                    fullWidth
                                    variant="contained"
                                    color="primary"
                                    className="submit"
                                    onClick={this.logout}
                                    >
                                    Log Out
                                </Button>
                            </Grid>
                        </Grid>
                    </Grid>

                    <Grid item xs={8}>
                        <Grid container>
                            <Grid item xs={5}>
                                <Button
                                    type="submit"
                                    fullWidth
                                    variant="contained"
                                    color="primary"
                                    className="submit"
                                    href = {"/Nurse/Assistant"}
                                    >
                                    Auxiliar
                                </Button>
                            </Grid>
                            <Grid item xs={1}></Grid>
                            <Grid item xs={5}>
                                <Button
                                    type="submit"
                                    fullWidth
                                    variant="contained"
                                    color="primary"
                                    className="submit"
                                    href = {"/Nurse/Patient"}
                                    >
                                    Paciente
                                </Button>
                            </Grid>
                        </Grid>
                    </Grid>

                    <Grid item xs={12} >
                        <Switch>
                            <Route path="/Nurse/Assistant">
                                <NAssistant />
                            </Route>
                            <Route path="/Nurse/Patient">
                                <NPatient />
                            </Route>
                        </Switch>
                    </Grid>
                </Grid>
            </div>
        )
    }
}
