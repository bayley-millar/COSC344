/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */




import network.JDBCConnection;
import domain.Staff;
import java.util.*;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class EmployeeSalary {
    public void printSalaries() {
        
        final JDBCConnection conn = new JDBCConnection();

	    final List<Staff> table = new ArrayList<>();
            final ResultSet result = conn.executeQuerySQL(
                    "SELECT s_fname, s_lname, salary FROM staff");
        try {
            while (result.next()) {
                Staff row = new Staff();
                row.setFirstName(result.getString("s_fname"));
                row.setLastName(result.getString("s_lname"));
                row.setSalary(Integer.valueOf(result.getString("salary")));
                table.add(row);
            }
        } catch (SQLException ex) {
            Logger.getLogger(EmployeeSalary.class.getName())
                    .log(Level.SEVERE, null, ex);
            System.err.println("EmployeeSalary: Unable to fetch data.");
        }

	    Collections.sort(table);

	    System.out.println();
	    for (Staff employee : table) {
		System.out.println(employee);
	    }
	    System.out.println();
    }
}


