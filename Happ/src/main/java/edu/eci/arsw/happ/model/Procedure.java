package edu.eci.arsw.happ.model;

import java.sql.Date;

public class Procedure {
    private int idProcedure;
    private String description;
    private boolean state;
    private Date date;

    public Procedure(int idProcedure, String description, boolean state, Date date){
        this.idProcedure = idProcedure;
        this.description = description;
        this.state = state;
        this.date = date;
    }

    public int getIdProcedure(){
        return idProcedure;
    }

    public String getDescription(){
        return description;
    }

    public boolean getState(){
        return state;
    }

    public Date getDate(){
        return date;
    }

    public void setIdProcedure(int idProcedure){
        this.idProcedure = idProcedure;
    }

    public void setDescription(String description){
        this.description = description;
    }

    public void setState(boolean state){
        this.state = state;
    }

    public void setDate(Date date){
        this.date = date;
    }
}