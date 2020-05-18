import { Button, FormControl, FormHelperText, Grid, InputLabel, NativeSelect, Paper, Typography } from '@material-ui/core';
import Axios from 'axios';
import React, { Component } from 'react';
import cookie from 'react-cookies';
import CustomTable from '../CustomTable';
import TextField from '@material-ui/core/TextField';

import SockJsClient from 'react-stomp';


export default class Nassistant extends Component {

    constructor(props) {
        super(props);
        this.state = { 
            nombre: '', 
            cedula: '',
            docType:'',
            genero:'',
            rh: '', 
            nHabitacion:'', 
            nPaciente:'',
            habitaciones:[],
            pacientes: [],
            numSelected: '',
            rowCount: '',
            rows: [],
            headCells: [],
            orderBy:'',
            order:'',
            nurseId:'',
            enfermeras:[],
            alerta: false,
            text:"",
            messages: [],
            typedMessage: "",
            name: "",
            usersend:"",
            user:""
        }
        this.logout = this.logout.bind(this)
    }

    headCells = [
        { id: 'IdProcedure', label: 'Id' },
        { id: 'nProcedure', label: 'Procedure' }
      ];
    sendMessage = () => {
        this.clientRef.sendMessage('/app/user-all', JSON.stringify({
            name: this.state.user,
            message: this.state.typedMessage
        }));
    };
    
    displayMessages = () => {
        return (
            <div>
                {this.state.messages.map(msg => {
                    return (
                        <div>
                            {this.state.name == msg.name ?
                                <div>
                                    <p className="title1">{msg.name} : </p><br/>
                                    <p>{msg.message}</p>
                                </div> :
                                <div>
                                    <p className="title2">{msg.name} : </p><br/>
                                    <p>{msg.message}</p>
                                </div>
                            }
                        </div>)
                })}
            </div>
        );
    };
    componentDidMount() {
        var jwtDecode = require('jwt-decode');
        var token = cookie.load('userToken');
        console.log(token);
        let deco = jwtDecode(token);
        console.log(deco.sub);
        console.log("usuario");
        console.log(deco.sub);
        this.setState({
            usersend:deco.sub
        })
        Axios.get("/assistant-nurse/boss/nurse/user/patriciaM") //cambiar patriciaM" por "+this.state.usersend
        .then(res =>{
            console.log(res.data.loginUser)
            this.setState({
                user:res.data.loginUser
            })
            
            
        });
        Axios.get("/assistant-nurse/nurses")
        .then(res =>{
            console.log(res.data)
            this.setState({
                enfermeras:res.data
            })
        });
        console.log(this.state.enfermeras.length);
        Axios.get("/assistant-nurse/patients")
        .then(res => {
            this.setState({
                pacientes:res.data
            })
        });
        
    }
    logout(){
        cookie.remove('userToken',{path:'/'})
        console.log(cookie.load('userToken'))
        this.props.history.push("/")
    }
    
    alert = (event) => {
        console.log("Alerta");
        this.setState({
            alert: true
        })
    }
    
    getNurse = (event) => {
        let idNurse = event.target.value;
        console.log(event.target.value);
        this.setState((state) => {
            for(const nurse of state.enfermeras){
                if(nurse.nurseId === parseInt(idNurse)){
                    console.log(nurse);
                    return({
                        nurseId: nurse.nurseId
                    })
                }
            }
        })
    }


    roomChange = (event) => {
        // console.log(event.target.value);
        this.setState({nHabitacion:event.target.value}, () => {
            this.load(this.state.nHabitacion);
        })
    }

    patientChange = (event) => {
        let idPacient = event.target.value;
        console.log(event.target.value);
        this.setState((state) => {
            for (const paciente of state.pacientes) {
                if (paciente.patientId === parseInt(idPacient)) {
                    console.log(paciente);
                    return ({
                        nombre: paciente.name,
                        docType: paciente.govType,
                        cedula: paciente.govId,
                        rh: paciente.rh,
                        genero: paciente.gender,
                        nPaciente: paciente.patientId
                    })
                }
            }
        })

    }

    onSelectAllClick = (event) => {
        if (event.target.checked) {
          const newSelecteds = this.state.rows.map((n) => n.name);
          this.setState(newSelecteds);
          return;
        }
        this.setState([]);
      }


    render() {
        return (
            <div>
            <div>
                <Grid container spacing={3}>
                    <Grid item xs={6} style={{ padding: 3 }}>
                        <Grid container spacing={3}>
                            <Grid item xs={6}>
                                <Button
                                    type="submit"
                                    fullWidth
                                    variant="contained"
                                    color="primary"
                                    className="submit"
                                    onClick = {this.logout}
                                    >
                                    Log Out
                                </Button>
                            </Grid>
                            <Grid item xs={6} >
                                <Button
                                    type="submit"
                                    fullWidth
                                    variant="contained"
                                    color="primary"
                                    className="submit"
                                    
                                    onClick = {this.alert}
                                    >
                                    Alerta
                                </Button>
                            </Grid>
                            {this.state.alert == true ? 
                              <div className="align-center">
                              <br/><br/>
                              <table>
                                  <tr>
                                      <td>
                                          <TextField id="outlined-basic" label="Escriba su alerta una alerta:" variant="outlined"
                                                     onChange={(event) => {
                                                         this.setState({typedMessage: event.target.value});
                                                     }}/>
                                      </td>
                                      <td>
                                          <Button variant="contained" color="primary"
                                                  onClick={this.sendMessage}>Enviar</Button>
                                      </td>
                                  </tr>
                              </table>
                          </div>
                            :
                            ""
                        }
                            <Grid container style={{marginBottom: "3%"}}>
                                <Grid item xs={2}></Grid>
                                <Grid item xs={6}  component={Paper} style={{ padding: 5 }}>
                                    <FormControl fullWidth>
                                        <InputLabel id="typeNurseInput">Enfermera</InputLabel>
                                        <NativeSelect 
                                            fullWidth
                                            value={this.state.nurseId}
                                            onChange={this.getNurse}
                                            >
                                            <option value=""> </option>
                                            {this.state.enfermeras.map((enfermera, index) => {
                                                return(
                                                <option key={index} value={enfermera.nurseId}> {enfermera.position} - {enfermera.name} </option>
                                                );
                                            })}
                                            
                                        </NativeSelect>
                                    </FormControl>
                                </Grid>
                            </Grid>

                            <Grid container style={{marginBottom: "3%"}}>

                                <Grid item xs={2}></Grid>
                                    <Grid item xs={6} component={Paper} style={{ padding: 5 }}>
                                    <FormControl fullWidth>
                                        <InputLabel htmlFor="habitacionSelect">Habitacón</InputLabel>
                                        <NativeSelect
                                            fullWidth
                                            value={this.state.nHabitacion}
                                            onChange={this.roomChange}
                                            inputProps={{
                                                name: 'Habitacion',
                                                id: 'habitacionSelect',
                                            }}
                                        >   <option value="" />
                                            {this.state.habitaciones.map((habitacion, index) => {
                                                return (
                                                    <option key={index} value={habitacion.idRoom}>Habitacion {habitacion.idRoom}</option>
                                                );
                                            })}
                                        </NativeSelect>
                                        <FormHelperText>Habitacion</FormHelperText>
                                    </FormControl>
                                </Grid>
                                <Grid item xs={2}></Grid>
                            </Grid>
                            
                            <Grid container>
                                <Grid item xs={2}></Grid>                                       
                                <Grid item xs={6} component={Paper} style={{padding: 5}}>
                                    <FormControl fullWidth>
                                        <InputLabel htmlFor="pacienteSelect">Paciente</InputLabel>
                                        <NativeSelect
                                            fullWidth
                                            value={this.state.nPaciente}
                                            onChange={this.patientChange}
                                            inputProps={{
                                                name: 'Paciente',
                                                id: 'pacienteSelect',
                                            }}
                                        >   <option value="" />
                                            {this.state.pacientes.map((paciente, index) =>{
                                                return(
                                                    <option key={index} value={paciente.patientId}>{paciente.patientId} - {paciente.name}</option>
                                                );
                                            })}
                                        </NativeSelect>
                                        <FormHelperText>Paciente</FormHelperText>
                                    </FormControl>
                                </Grid>
                                <Grid item xs={2}></Grid>
                            </Grid>
                        </Grid>
                    </Grid>

                    <Grid item xs={6} style={{marginTop: "3%"}}>
                        <Grid container component={Paper}>
                            <Grid item xs={12}>
                                <Typography>
                                    Nombre:  { this.state.nombre }
                                </Typography>
                            </Grid>
                            <Grid item xs={12}>
                                <Typography>
                                    Tipo de documento:  { this.state.docType }
                                </Typography>
                            </Grid>
                            <Grid item xs={12}>
                                <Typography>
                                    Cedula:  { this.state.cedula }
                                </Typography>
                            </Grid>
                            <Grid item xs={12}>
                                <Typography>
                                    RH:  { this.state.rh }
                                </Typography>
                            </Grid>
                            <Grid item xs={12}>
                                <Typography>
                                    Genero:  { this.state.genero }
                                </Typography>
                            </Grid>
                        </Grid>

                        <Grid container component={Paper} style={{marginTop: "3%"}}>
                            <Grid item xs={12}>
                                <CustomTable rows={this.state.rows} headCells={this.headCells} title={"Procedures"} />
                            </Grid>
                        </Grid>
                    </Grid>

                    

                </Grid>
            </div>
                <SockJsClient url='http://localhost:8081/websocket-chat/'
                              topics={['/topic/user']}
                              onConnect={() => {
                                  console.log("connected");
                              }}
                              onDisconnect={() => {
                                  console.log("Disconnected");
                              }}
                              onMessage={(msg) => {
                                  var jobs = this.state.messages;
                                  jobs.push(msg);
                                  this.setState({messages: jobs});
                                  console.log(this.state);
                              }}
                              ref={(client) => {
                                  this.clientRef = client
                              }}/>
            
            </div>
        )
    }

}
