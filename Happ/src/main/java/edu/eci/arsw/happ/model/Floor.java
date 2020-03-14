package edu.eci.arsw.happ.model;

public class Floor{
   private int idFloor;
   private int capacity;
   private boolean state;
   
   public Floor(int idFloor, int capacity, boolean state){
       this.idFloor = idFloor;
       this.capacity = capacity;
       this.state = state;
   }

   public int getIdFloor(){
       return idFloor;
   }

   public int getCapacity(){
       return capacity;
   }

   public boolean getState(){
       return state;
   }

   public void setIdFloor(int idFloor){
       this.idFloor = idFloor;
   }

   public void setCapacity(int capacity){
       this.capacity = capacity;
    }

    public void setState(boolean state){
        this.state = state;
    }
}