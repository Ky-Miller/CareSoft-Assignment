package com.caresoft.clinicapp;

import java.util.ArrayList;
import java.util.Date;

public class AdminUser extends User implements HIPAACompliantUser, HIPAACompliantAdmin {
	private Integer employeeID;
    private String role;
    private ArrayList<String> securityIncidents	= new ArrayList<String>();
    
    // TO DO: Implement a constructor that takes an ID and a role
    public AdminUser(Integer id, String role) {
    	this.role = role;
    	this.id  = id;
    	this.employeeID = id;
    }
    // TO DO: Implement HIPAACompliantUser!
    public boolean assignPin(int pin) {
		if(pin < 99999 || pin > 999999) {
			return false;
		}
			this.pin = pin;
			return true;
	}
    
    // TO DO: Implement HIPAACompliantAdmin!
    
    @Override
    public ArrayList<String> reportSecurityIncidents() {
    	return this.securityIncidents;
    }
    @Override
    public boolean accessAuthorized(Integer confirmedAuthID) {
    	if(this.id != confirmedAuthID) {
    		this.authIncident();
    		return false;
    	}
    	return true;
    }
    
    public void newIncident(String notes) {
        String report = String.format(
            "Datetime Submitted: %s \n,  Reported By ID: %s\n Notes: %s \n", 
            new Date(), this.id, notes
        );
        securityIncidents.add(report);
    }
	public void authIncident() {
        String report = String.format(
            "Datetime Submitted: %s \n,  ID: %s\n Notes: %s \n", 
            new Date(), this.id, "AUTHORIZATION ATTEMPT FAILED FOR THIS USER"
        );
        securityIncidents.add(report);
    }
	// TO DO: Setters & Getters

	public Integer getEmployeeID() {
		return employeeID;
	}
	public void setEmployeeID(Integer employeeID) {
		this.employeeID = employeeID;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
    
	
}
