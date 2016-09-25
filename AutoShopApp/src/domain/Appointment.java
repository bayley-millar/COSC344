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
public class Appointment {
    private String pickupDate;
    private String id;
    private String dropOffDate;
    private String carId;
    private String workToDo;

    public Appointment(String pickupDate,
            String id,
            String dropOffDate,
            String carId,
            String workToDo) {
        this.pickupDate = pickupDate;
        this.id = id;
        this.dropOffDate = dropOffDate;
        this.carId = carId;
        this.workToDo = workToDo;
    }
    
    public String getPickupDate() {
        return pickupDate;
    }

    public void setPickupDate(String pickupDate) {
        this.pickupDate = pickupDate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDropOffDate() {
        return dropOffDate;
    }

    public void setDropOffDate(String dropOffDate) {
        this.dropOffDate = dropOffDate;
    }

    public String getCarId() {
        return carId;
    }

    public void setCarId(String carId) {
        this.carId = carId;
    }

    public String getWorkToDo() {
        return workToDo;
    }

    public void setWorkToDo(String workToDo) {
        this.workToDo = workToDo;
    }   
    
    public String toString() {
        return getId() + " " + getCarId() + " " + getPickupDate() + 
                " " + getDropOffDate()  + " " + getWorkToDo();
    }
}
