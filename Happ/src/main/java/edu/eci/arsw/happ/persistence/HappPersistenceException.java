/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.happ.persistence;

/**
 *
 * @author jualme
 */
public class HappPersistenceException extends Exception {
     public HappPersistenceException(String message) {
        super(message);
    }

    public HappPersistenceException(String message, Throwable cause) {
        super(message, cause);
    }
}
