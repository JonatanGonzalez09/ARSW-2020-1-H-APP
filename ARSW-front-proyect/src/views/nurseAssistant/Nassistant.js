import { Button, FormControl, FormHelperText, Grid, InputLabel, NativeSelect, Paper, Typography } from '@material-ui/core';
import Axios from 'axios';
import { Client } from '@stomp/stompjs';
import React, { Component } from 'react';
import cookie from 'react-cookies';
import CustomTable from '../CustomTable';


export default class Nassistant extends Component {

    state = {
        serverTime: null,
    }
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
        }
        this.logout = this.logout.bind(this)
    }

    headCells = [
        { id: 'IdProcedure', label: 'Id' },
        { id: 'nProcedure', label: 'Procedure' }
      ];

    componentDidMount() {
        var token = cookie.load('userToken');
        console.log(token);
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
    
            this.client.subscribe('/queue/now', message => {
              console.log(message);
              this.setState({serverTime: message.body});
            });
    
            this.client.subscribe('/topic/greetings', message => {
              alert(message.body);
            });
          },
          // Helps during debugging, remove in production
          debug: (str) => {
            console.log(new Date(), str);
          }
        });
    
        this.client.activate();
    }
    clickHandler = (event) => {
        console.log(this.text);
        this.setState({value: event.target.text});
      this.client.publish({destination: '/app/greetings', body:this.state.text});
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
    datachange = (event) => {
        console.log(event.target.value);
        this.setState({
            text: event.target.value
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
                             <form onSubmit={this.clickHandler}>
                            <Grid container style={{marginBottom: "3%"}}>
                            <Grid item xs={2}></Grid>                                       
                            <Grid item xs={6} component={Paper} style={{padding: 5}}>
                            <input type="text" value={this.state.text} onChange={this.datachange} />
                            </Grid>
                            <input type="submit" value="Submit" />
                            <Grid item xs={2}></Grid>
                        </Grid>
                        </form>
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
        )
    }

}
