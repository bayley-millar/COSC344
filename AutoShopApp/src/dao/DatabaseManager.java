/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import domain.Appointment;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;
import network.JDBCConnection;

/**
 *
 * @author reuben
 */
public class DatabaseManager {

    private final JDBCConnection conn = new JDBCConnection();

    public Collection<Appointment> getAppointments() {
        final String sql = "SELECT * FROM appointment ORDER BY id;";
        final ArrayList<Appointment> appointments = new ArrayList<>();

        try {
            final PreparedStatement stmt = conn.createPreparedStatement(sql);
            final ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                final String id = rs.getString("id");
                final String carId = rs.getString("carId");
                final Timestamp pickupTime = rs.getTimestamp("name");
                final Timestamp dropOffTime = rs.getTimestamp("description");
                final String workToDo = rs.getString("category");

                appointments.add(new Appointment(
                        pickupTime,
                        id,
                        dropOffTime,
                        carId,
                        workToDo));
            }
            return appointments;
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseManager.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
        
        return appointments;
    }
    
    public Appointment getAppointmentById(String searchId){
        final String sql = "SELECT * FROM appointment WHERE id = ?;";
        Appointment a = null;

        try {
            final PreparedStatement stmt = conn.createPreparedStatement(sql); 
 
            stmt.setString(1, searchId);
            
            final ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                final String id = rs.getString("id");
                final String carId = rs.getString("carId");
                final Timestamp pickupTime = rs.getTimestamp("name");
                final Timestamp dropOffTime = rs.getTimestamp("description");
                final String workToDo = rs.getString("category");

                a = new Appointment(
                        pickupTime,
                        id,
                        dropOffTime,
                        carId,
                        workToDo);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseManager.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
        
        return a;
    }

    public void addAppointment(Appointment a) {
        final String sql = "MERGE INTO appointment"
                + " (ap_id, pickup_time, time_booked_for, car_id)";
        try {
            final PreparedStatement stmt
                    = conn.createPreparedStatement(sql);

            stmt.setTimestamp(1, a.getPickupTime());
            stmt.setString(2, a.getId());
            stmt.setTimestamp(3, a.getDropOffTime());
            stmt.setString(4, a.getCarId());
            stmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseManager.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
    }
    
    public void deleteAppointnment(Appointment a){
        final String sql = "DELETE FROM appointment WHERE id = ?;";
        try {
            final PreparedStatement stmt
                    = conn.createPreparedStatement(sql);
            stmt.setString(1, a.getId());
            stmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseManager.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
    }
}
