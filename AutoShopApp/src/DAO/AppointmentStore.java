/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import domain.Appointment;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author reuben
 */
public class AppointmentStore {
    
    public void saveAppointment(Appointment app) {
        String sql = "merge into appointments ("
                + "pickup_time, time_booked_for, category, price,"
                + "quantity) values (?,?,?,?,?,?)";
        try (
                Connection dbCon = JdbcConnection.getConnection(URL);
                PreparedStatement stmt = dbCon.prepareStatement(sql);) {
            stmt.setInt(1, p.getProductId());
            stmt.setString(2, p.getName());
            stmt.setString(3, p.getDescription());
            stmt.setString(4, p.getCategory());
            stmt.setDouble(5, p.getPrice());
            stmt.setDouble(6, p.getQuantityInStock());
            stmt.executeUpdate();
        } catch (SQLException ex) {
            throw new DAOException(ex.getMessage(), ex);
        }
    }
}
