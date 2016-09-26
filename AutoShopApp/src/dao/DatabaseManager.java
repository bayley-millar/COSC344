package dao;

import domain.Account;
import domain.Appointment;
import domain.Customer;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.sql.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import network.JDBCConnection;
import util.TimeUtil;

/**
 * This class handles changes to the database
 * through various SQL statements.
 */
public class DatabaseManager {

    private final JDBCConnection conn = new JDBCConnection();
    
    /**
     * Returns a Collection of all appointments in the database. 
     * 
     * @return Collection object, appointments in the database.
    **/
    public Collection<Appointment> getAppointments() {
        final ArrayList<Appointment> appointments = new ArrayList<>();
        //Sql statement gets all columns from appointment and work_to_do 
        //column from appointment_work_to_do.
        final String appointmentSql =
            "SELECT ap_id, pickup_date, drop_off_date, car_id, work_to_do FROM "
                + "appointment, appointment_work_to_do WHERE ap_id = w_ap_id";
        try {
            final PreparedStatement stmt = conn.createPreparedStatement(
                    appointmentSql);
            final ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                final String id = rs.getString("ap_id");
                final Date pickupDate = rs.getDate("pickup_date");
                final Date dropOffDate = rs.getDate("drop_off_date");
                final String pickupDateStr = 
                        TimeUtil.convertStringToDate(pickupDate);
                final String dropOffDateStr = 
                        TimeUtil.convertStringToDate(dropOffDate);
                final String carId = rs.getString("car_id");
                final String workToDo = rs.getString("work_to_do");

                appointments.add(new Appointment(
                        pickupDateStr,
                        id,
                        dropOffDateStr,
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
    /**
     * This query will return the customer with the highest balance.
     * this is then pushed to the GUI for display
     * @return account collection of the account data 
     */
    public Collection<Account> getMax(){
        final ArrayList<Account> account = new ArrayList<>();
        final String maxSql =
            "select * from account where balance = (select max(balance) from account where last_visit >= TO_DATE('25-11-1980','DD-MM-YYYY'))";
        try {
            final PreparedStatement stmt = conn.createPreparedStatement(maxSql);
            final ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                final String id = rs.getString("acc_id");
                final int balance = rs.getInt("balance");
                final Date last_visit = rs.getDate("last_visit");
                final String lastVisitedStr = 
                        TimeUtil.convertStringToDate(last_visit);

                account.add(new Account(
                        lastVisitedStr,
                        balance,
                        id));
            }
            return account;
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseManager.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
        
        return account;
    }
    
    /**
     * This query gets the customers with a balance over the average amount.
     * This is also sent to the GUI for display
     * @return  Account collection
     */
    public Collection<Account> getAverage(){
        final ArrayList<Account> account2 = new ArrayList<>();
        final String aboveAvgSql =
            "select * from account b where balance > (select avg(balance) from account)";
        try {
            final PreparedStatement stmt = conn.createPreparedStatement(aboveAvgSql);
            final ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                final String id = rs.getString("acc_id");
                final int balance = rs.getInt("balance");
                final Date last_visit = rs.getDate("last_visit");
                final String lastVisitedStr = 
                        TimeUtil.convertStringToDate(last_visit);

                account2.add(new Account(
                        lastVisitedStr,
                        balance,
                        id));
            }
            return account2;
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseManager.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
        
        return account2;
    }
       
    /**
     * This query returns all the customers that have more than one car in the
     * data base. This is then sent to the GUI for display.
     * @return customer a collection of customer data 
     */
    public Collection<Customer> getMoreThanOneCar(){
        final ArrayList<Customer> customer = new ArrayList<>();
        final String moreThanSql =
            "select c_id, c_fname, c_lname from customer c where 1 < (SELECT count(*) from car where c_id = c.c_id)";
        try {
            final PreparedStatement stmt = conn.createPreparedStatement(moreThanSql);
            final ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                final String firstName = rs.getString("c_fname");
                final String lastName = rs.getString("c_lname");
                final String id = rs.getString("c_id");

                customer.add(new Customer(
                        firstName,
                        lastName,
                        id
                        ));
            }
            return customer;
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseManager.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
        
        return customer;
    }
    /**
     * This method returns an appointment given the searchId.
     * @param String searchId, the ID of the appointment.
     * @return Appointment object associated with the searchId.
    **/
    public Appointment getAppointmentById(String searchId){
        // Selects all columns from appointment and appointment_work_to_do where
        // appointment id are the same.
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
                final String pickupDate = (String) rs.getObject("pickup_date");
                final String dropOffDate =
                        (String) rs.getObject("drop_off_date");
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
    
    /**
     * Adds a appointment to the database.
     * 
     * @param Appointment a, new appointment object.
    **/
    public void addAppointment(Appointment a) {
        /**
         * appointmentSql SQL statement inserts a new appointment into the db.
         * workToDoSql SQL statement inserts a new appointment_work_to_do
         * into the db.
         */
        final String appointmentSql = "INSERT INTO appointment VALUES(?,TO_DATE"
                + "(?, 'DD-MM-YYYY'),TO_DATE(?, 'DD-MM-YYYY'),?)";
        final String workToDoSql = "INSERT INTO appointment_work_to_do VALUES"
                + " (?,?)";
        try {
            final PreparedStatement appStmt
                    = conn.createPreparedStatement(appointmentSql);
            final PreparedStatement workStmt
                    = conn.createPreparedStatement(workToDoSql);
            
            appStmt.setString(1, a.getId());
            appStmt.setString(2,a.getPickupDate());
            appStmt.setString(3,a.getDropOffDate());
            appStmt.setString(4, a.getCarId());

            workStmt.setString(1, a.getId());
            workStmt.setString(2, a.getWorkToDo());

            appStmt.executeUpdate();
            workStmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseManager.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
    }
    /**
     * This method deletes an appointment from the database.
     * @param Appointment a, appointment object to be deleted.
    **/
    public void deleteAppointnment(Appointment a){
        /**
         * Deletes appointment from db. To do this everything related to that
         * appointment must also be deleted. 
         */
        final String appSql = "DELETE FROM appointment WHERE ap_id = ?";
        final String workSql = "DELETE FROM appointment_work_to_do WHERE w_ap_id = ?";
        final String partSql = "DELETE FROM parts WHERE p_app_id = ?";
        final String respSql = "DELETE FROM responsible_for WHERE r_ap_id = ?";


        try {
            final PreparedStatement appStmt
                    = conn.createPreparedStatement(appSql);
            final PreparedStatement workStmt
                    = conn.createPreparedStatement(workSql);
            final PreparedStatement partStmt
                    = conn.createPreparedStatement(partSql);
            final PreparedStatement respStmt
                    = conn.createPreparedStatement(respSql);
            
            appStmt.setString(1, a.getId());
            workStmt.setString(1, a.getId());
            partStmt.setString(1, a.getId());
            respStmt.setString(1, a.getId());

            workStmt.executeUpdate();
            partStmt.executeUpdate();
            respStmt.executeUpdate();
            
            appStmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseManager.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Returns a list of the carIds from the car table.
     * 
     * @return 
     */
    public Collection<String> getCarIds(){
        final ArrayList<String> ids = new ArrayList<>();
        final String carSql =
            "SELECT car_id from car";
        try {
            final PreparedStatement stmt = conn.createPreparedStatement(carSql);
            final ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                final String id = rs.getString("car_id");
                ids.add(id);
            }
            return ids;
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseManager.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
        
        return ids;
    }
    
    
}
