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
        final ArrayList<Appointment> appointments = new ArrayList<>();
        final String appointmentSql =
                "SELECT ap_id, pickup_time, drop_off_time, car_id,"
                + " work_to_do FROM appointment, appointment_work_to_do"
                + " WHERE ap_id = w_ap_id;";
        try {
            final PreparedStatement stmt = conn.createPreparedStatement(appointmentSql);
            final ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                final String id = rs.getString("ap_id");
                final Timestamp pickupTime = rs.getTimestamp("pickup_time");
                final Timestamp dropOffTime = rs.getTimestamp("drop_off_time");
                final String carId = rs.getString("car_id");
                final String workToDo = rs.getString("work_to_do");

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
        final String sql =
                "SELECT * FROM appointment, appointment_work_to_do"
                + " WHERE ap_id = w_ap_id AND ap_id = ?";
        Appointment a = null;

        try {
            final PreparedStatement stmt = conn.createPreparedStatement(sql); 
 
            stmt.setString(1, searchId);
            
            final ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                final String id = rs.getString("ap_id");
                final Timestamp pickupTime = rs.getTimestamp("pickup_time");
                final Timestamp dropOffTime = rs.getTimestamp("drop_off_time");
                final String carId = rs.getString("car_id");
                final String workToDo = rs.getString("work_to_do");

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
        final String appointmentSql = "MERGE INTO appointment"
                + " (ap_id, pickup_time, drop_off_time, car_id);";
        final String workToDoSql = "MERGE INTO appointment_work_to_do"
                + " (w_ap_id, work_to_do);";
        try {
            final PreparedStatement appStmt
                    = conn.createPreparedStatement(appointmentSql);
            final PreparedStatement workStmt
                    = conn.createPreparedStatement(workToDoSql);
            
            appStmt.setString(1, a.getId());
            appStmt.setTimestamp(2, a.getPickupTime());
            appStmt.setTimestamp(3, a.getDropOffTime());
            appStmt.setString(4, a.getCarId());
            
            workStmt.setString(1, a.getId());
            appStmt.setString(2, a.getWorkToDo());

            appStmt.executeUpdate();
            workStmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseManager.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
    }
    
    public void deleteAppointnment(Appointment a){
        final String appSql = "DELETE FROM appointment WHERE ap_id = ?;";
        final String workSql = "DELETE FROM work_to_do WHERE w_ap_id = ?;";

        try {
            final PreparedStatement appStmt
                    = conn.createPreparedStatement(appSql);
            final PreparedStatement workStmt
                    = conn.createPreparedStatement(workSql);
            
            appStmt.setString(1, a.getId());
            workStmt.setString(1, a.getId());
            appStmt.executeUpdate();
            workStmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseManager.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
    }
}
