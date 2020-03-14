package edu.eci.arsw.happ.model;

public class Bed {
    private int idBed;
    private boolean state;

    public Bed(int idBed, boolean state){
        this.idBed = idBed;
        this.state = state;
    }

    public int getIdBed(){
        return idBed;
    }

    public boolean getStateBed(){
        return state;
    }

    public void setIdBed(int idBed){
        this.idBed = idBed;
    }

    public void setStateBed(boolean state){
        this.state = state;
    }
}