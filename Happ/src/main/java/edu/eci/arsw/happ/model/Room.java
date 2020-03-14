package edu.eci.arsw.happ.model;

public class Room {
    private int idRoom;
    private boolean state;

    public Room(int idRoom, boolean state){
        this.idRoom = idRoom;
        this.state = state;
    }

    public int getIdRoom(){
        return idRoom;
    }

    public boolean getStateRoom(){
        return state;
    }

    public void setIdRoom(int idRoom){
        this.idRoom = idRoom;
    }

    public void setStateRoom(boolean state){
        this.state = state;
    }
}