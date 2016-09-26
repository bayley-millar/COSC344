/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

/**
 *
 * @author rstorr
 */
public class Customer {
    private String firstName;
    private String lastName;
    private String address;
    private String id;
    private String gender;

    public Customer(String firstName,
            String lastName,
            String address,
            String id,
            String gender) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.id = id;
        this.gender = gender;
    }
    
        public Customer(String firstName,
            String lastName,
            String id) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.id = id;
        ;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getAddress() {
        return address;
    }

    public String getId() {
        return id;
    }

    public String getGender() {
        return gender;
    }


    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    @Override
    public String toString() {
        return "firstName= " + firstName + ", lastName= " + lastName + ", id= " + id ;
    }
    
    

}
