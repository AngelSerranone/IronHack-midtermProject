package com.ironhack.midtermProject.enums;

public enum AccountStatus {
    FROZEN("Frozen"),
    ACTIVE("Active");

    private final String status;

    AccountStatus(String status) {this.status = status;}

    public String getStatus() {return status;}
}
