/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.util.ArrayList;

/**
 *
 * @author rstorr
 */
public class Staff implements Comparable<Staff>{
    private int staffId;
    private String address;
    private String dateOfBirth;
    private int salary;
    private String firstName;
    private String lastName;
    private int phoneNumber;
    private ArrayList<Integer> partsOrderedId; 
    
    public Staff(){}

    public Staff(int staffId, String address, String dateOfBirth,
            int salary, String firstName, String lastName,
            int phoneNumber, ArrayList<Integer> partsOrderedId) {
        this.staffId = staffId;
        this.address = address;
        this.dateOfBirth = dateOfBirth;
        this.salary = salary;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.partsOrderedId = partsOrderedId;
    }

    public int getStaffId() {
        return staffId;
    }

    public void setStaffId(int staffId) {
        this.staffId = staffId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public ArrayList<Integer> getPartsOrderedId() {
        return partsOrderedId;
    }

    public void setPartsOrderedId(ArrayList<Integer> partsOrderedId) {
        this.partsOrderedId = partsOrderedId;
    }

    @Override
    public int compareTo(Staff other) {
        if (this.salary < other.salary) {
	    return -1;
	}

	if (this.salary > other.salary) {
	    return 1;
	}
	
        return 0;
    }
    
    
}
