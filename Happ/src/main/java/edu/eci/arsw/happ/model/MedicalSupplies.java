package edu.eci.arsw.happ.model;

public class MedicalSupplies{
    private int code;
    private String name;
    private int quantity;
    private String methodOfUse;

    public MedicalSupplies(int code, String name, int quantity, String methodOfUse){
        this.code = code;
        this.name = name;
        this.quantity = quantity;
        this.methodOfUse = methodOfUse;
    }

    public int getCode(){
        return code;
    }

    public String getName(){
        return name;
    }

    public int getQuantity(){
        return quantity;
    }

    public String getMethodOfUse(){
        return methodOfUse;
    }

    public void setCode(int code){
        this.code = code;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setQuantity(int quantity){
        this.quantity = quantity;
    }

    public void setMethodOfUse(String methodOfUse){
        this.methodOfUse = methodOfUse;
    }
}