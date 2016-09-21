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
    private String pickupTime;
    private String dropOffTime;
    private String dropOffDate;
    private String carId;
    private String workToDo;

    public Appointment(String pickupTime,
            String dropOffTime, String dropOffDate, String carId, String workToDo) {
        this.pickupTime = pickupTime;
        this.dropOffTime = dropOffTime;
        this.dropOffDate = dropOffDate;
        this.carId = carId;
        this.workToDo = workToDo;
    }

    public String getPickupTime() {
        return pickupTime;
    }

    public void setPickupTime(String pickupTime) {
        this.pickupTime = pickupTime;
    }

    public String getDropOffTime() {
        return dropOffTime;
    }

    public void setDropOffTime(String dropOffTime) {
        this.dropOffTime = dropOffTime;
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
    
    
    
}
