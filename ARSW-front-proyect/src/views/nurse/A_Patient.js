import React, { Component } from 'react'
import cookie from 'react-cookies'
import { Grid, FormControl, InputLabel, NativeSelect, Paper, Button, Typography } from '@material-ui/core'
import Axios from 'axios'
import AsignarCuarto from './AsignarCuarto';
import AsignarProcedimiento from './AsignarProcedimiento';

export default class A_Patient extends Component {
    constructor(props) {
        super(props);
        this.state = { 
            pacientes: [],
            P_name:'',
            P_id:'',
            P_direccion:'',
            P_genero:'',
            P_idDoc:'',
            P_idType:'',
            P_telefono:'',
            P_rh:'',
            vistaSelect:''

        }
    }

    componentDidMount() {
        var token = cookie.load('userToken');
        console.log(token);
        Axios.get("/nurse/patients")
        .then(res =>{
            console.log(res.data)
            this.setState({
                pacientes:res.data
            })
        });
        
    }
    
    getPatient = (event) => {
        let auxPaciente = event.target.value;
        //console.log(event.target.value);
        
        for(const paci of this.state.pacientes){
            if(paci.patientId === parseInt(auxPaciente)){
                Axios.get("/nurse/patient/" + paci.patientId)
                .then(res => {
                    this.setState({
                        P_name: res.data.name,
                        P_id:res.data.patientId,
                        P_direccion:res.data.address,
                        P_genero:res.data.gender,
                        P_idDoc:res.data.govId,
                        P_idType:res.data.govType,
                        P_telefono:res.data.phone,
                        P_rh:res.data.rh
                    })

                })
                
            }
        }
    }

    selectButton = (event, value) => {
        event.preventDefault();
        this.setState ({
            vistaSelect:value
        })
    }

    vistaRender = ()=>{
        if(this.state.vistaSelect === "cuarto")
            return <AsignarCuarto/>
        else if(this.state.vistaSelect === "procedimiento")
            return <AsignarProcedimiento />
        else
            return ""
    }
    

    render() {
        return (
            <div>
                
                <Grid item xs={12}>
                    <Typography variant="h2" gutterBottom align="center" color='textSecondary'>
                        Asignar Proceso
                    </Typography>
                </Grid>
                <Grid container style={{paddingTop:'2%'}}>
                    <Grid item xs={11} component={Paper} style={{ padding: "2%", marginBottom: "2%" }}>
                        <FormControl fullWidth error={this.state.errorType}>
                            <InputLabel id="typeNurseInput">Paciente</InputLabel>
                            <NativeSelect 
                                fullWidth
                                value={this.state.P_id}
                                onChange={this.getPatient}
                                >
                                <option value=""> </option>
                                {this.state.pacientes.map((paciente, index) => {
                                    return(
                                    <option key={index} value={paciente.patientId}> {paciente.patientId} - {paciente.name} </option>
                                    );
                                })}
                                
                            </NativeSelect>
                        </FormControl>
                    </Grid>

                    <Grid item xs={11} component={Paper} style={{ padding: "2%", marginBottom: "2%" }}>
                        <Grid container >
                            <Grid item xs={5}>
                                <Button
                                    type="submit"
                                    fullWidth
                                    variant="contained"
                                    color="primary"
                                    className="submit"
                                    onClick={(e)=>this.selectButton(e,"cuarto")}
                                    >
                                    Asignar Cuarto
                                </Button>
                            </Grid>
                            <Grid item xs={2}></Grid>
                            <Grid item xs={5}>
                                <Button
                                    type="submit"
                                    fullWidth
                                    variant="contained"
                                    color="primary"
                                    className="submit"
                                    onClick={(e)=>this.selectButton(e,"procedimiento")}
                                    >
                                    Asignar Procedimiento
                                </Button>
                            </Grid>
                        </Grid>
                    </Grid>
                    <Grid item xs={11} component={Paper} style={{ padding: "2%", marginBottom: "2%" }}>
                        {this.vistaRender()}
                    </Grid>
                </Grid>
            </div>
        )
    }
}
