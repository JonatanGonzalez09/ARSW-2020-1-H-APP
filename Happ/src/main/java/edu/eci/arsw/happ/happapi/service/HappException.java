package edu.eci.arsw.happ.happapi.service;

public class HappException extends Exception{

    private static final long serialVersionUID = 1L;

    public HappException(String message) {
        super(message);
    }
}