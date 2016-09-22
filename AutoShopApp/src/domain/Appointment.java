/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import util.TimeUtil;
import java.sql.Timestamp;


/**
 *
 * @author rstorr
 */
public class Appointment {
    private Timestamp pickupTime;
    private String id;
    private Timestamp dropOffTime;
    private String carId;
    private String workToDo;

    public Appointment(String pickupTime,
            String id,
            String dropOffTime,
            String carId,
            String workToDo) {
        this.pickupTime = TimeUtil.getTimeStamp(pickupTime);
        this.id = id;
        this.dropOffTime = TimeUtil.getTimeStamp(dropOffTime);;
        this.carId = carId;
        this.workToDo = workToDo;
    }
    
    public Appointment(Timestamp pickupTime,
            String id,
            Timestamp dropOffTime,
            String carId,
            String workToDo) {
        this.pickupTime = pickupTime;
        this.id = id;
        this.dropOffTime = dropOffTime;
        this.carId = carId;
        this.workToDo = workToDo;
    }

    public Timestamp getPickupTime() {
        return pickupTime;
    }

    public void setPickupTime(String pickupTime) {
        this.pickupTime = TimeUtil.getTimeStamp(pickupTime);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Timestamp getDropOffTime() {
        return dropOffTime;
    }

    public void setDropOffTime(String dropOffTime) {
        this.dropOffTime = TimeUtil.getTimeStamp(dropOffTime);
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
