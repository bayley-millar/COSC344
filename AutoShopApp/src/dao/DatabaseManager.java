package dao;

import domain.Appointment;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.sql.Date;
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
            "select ap_id, pickup_date, drop_off_date, car_id, work_to_do from appointment, appointment_work_to_do where ap_id = w_ap_id;";
        try {
            final PreparedStatement stmt = conn.createPreparedStatement(appointmentSql);
            final ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                final String id = rs.getString("ap_id");
                final Date pickupDate = rs.getDate("pickup_date");
                final Date dropOffDate = rs.getDate("drop_off_date");
                final String carId = rs.getString("car_id");
                final String workToDo = rs.getString("work_to_do");

                appointments.add(new Appointment(
                        pickupDate,
                        id,
                        dropOffDate,
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
                final Date pickupDate = rs.getDate("pickup_date");
                final Date dropOffDate = rs.getDate("drop_off_date");
                final String carId = rs.getString("car_id");
                final String workToDo = rs.getString("work_to_do");

                a = new Appointment(
                        pickupDate,
                        id,
                        dropOffDate,
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
        final String appointmentSql = "INSERT INTO appointment VALUES(?,?,?,?)";
        final String workToDoSql = "INSERT INTO appointment_work_to_do VALUES (?,?)";
        try {
            final PreparedStatement appStmt
                    = conn.createPreparedStatement(appointmentSql);
            final PreparedStatement workStmt
                    = conn.createPreparedStatement(workToDoSql);
            
            appStmt.setString(1, a.getId());
            appStmt.setDate(2, a.getPickupDate());
            appStmt.setDate(3, a.getDropOffDate());
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
