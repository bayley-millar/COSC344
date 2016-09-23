/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import util.TimeUtil;
import oracle.sql.DATE;


/**
 *
 * @author rstorr
 */
public class Appointment {
    private DATE pickupDate;
    private String id;
    private DATE dropOffDate;
    private String carId;
    private String workToDo;

    public Appointment(String pickupDate,
            String id,
            String dropOffDate,
            String carId,
            String workToDo) {
        this.pickupDate = TimeUtil.getDate(pickupDate);
        this.id = id;
        this.dropOffDate = TimeUtil.getDate(dropOffDate);
        this.carId = carId;
        this.workToDo = workToDo;
    }
    
    public Appointment(DATE pickupTime,
            String id,
            DATE dropOffTime,
            String carId,
            String workToDo) {
        this.pickupDate = pickupTime;
        this.id = id;
        this.dropOffDate = dropOffTime;
        this.carId = carId;
        this.workToDo = workToDo;
    }

    public DATE getPickupDate() {
        return pickupDate;
    }

    public void setPickupDate(String pickupDate) {
        this.pickupDate = TimeUtil.getDate(pickupDate);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public DATE getDropOffDate() {
        return dropOffDate;
    }

    public void setDropOffDate(String dropOffDate) {
        this.dropOffDate = TimeUtil.getDate(dropOffDate);
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
