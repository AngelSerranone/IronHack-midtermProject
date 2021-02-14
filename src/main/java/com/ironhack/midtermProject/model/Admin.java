package com.ironhack.midtermProject.model;
import javax.persistence.Entity;

@Entity
public class Admin extends User {

    /** --------------------Properties-------------------- **/

    /** --------------------Constructor---------------------- **/

    /** Empty Constructor **/
    public Admin() {
    }

    /** Constructor **/
    public Admin(String name, String username, String password) {
        super(name, username, password);
    }

    /** -----------------Getters & Setters------------------ **/


}
