/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package default_package;



import java.util.*;
import java.sql.*;


/**
 * This program retrieves the first and last names and salary
 * of all employees into an array-list of objects.  It then 
 * sorts the array-list by salary and prints the data
 *
 * @author Paul Werstein (2003)
 * @author Raymond (2012)
 */
public class EmployeeSalary {


    

    /** This is the function that does all the work
     * -- connect to the database
     * -- perform the query
     * -- load the data into a data structure
     * -- sort the data
     * -- output the data
     */
    public void go() {

	// Read pass.dat
	//UserPass login = new UserPass();
	String user = "jbenn";//login.getUserName();
	String pass = "cs1790302";//login.getPassWord();
	String host = "silver";   //login.getHost();

	Connection con = null;
	try {
	    // Register the driver and connect to Oracle
	    DriverManager.registerDriver 
		(new oracle.jdbc.driver.OracleDriver());
            

	    String url = "jdbc:oracle:thin:@" + host + ":1527:cosc344";
	    con = DriverManager.getConnection(url, user, pass);
	    System.out.println("Connected to Oracle");

	    Statement stmt = con.createStatement();

	    // Create the SQL statement and execute it.
	    String sql = new String("SELECT s_fname, s_lname, salary " +
				    "FROM staff");
	    System.out.println("SQL: " + sql);
	    ResultSet result = stmt.executeQuery(sql);

	    // Create an array list and put the data into it.
	    // EmployeeData holds one row.
	    List<EmployeeData> table = new ArrayList<EmployeeData>();
	    while (result.next()) {
		EmployeeData row = new EmployeeData(
					result.getString("s_fname"),
					result.getString("s_lname"),
					result.getInt("salary"));
		table.add(row);
	    }

	    // Sort the table
	    Collections.sort(table);

	    // Output the sorted data (no real formatting).
	    System.out.println();
	    for (EmployeeData employee : table) {
		System.out.println(employee);
	    }
	    System.out.println();

	} catch (SQLException e) {
	    quit(e.getMessage());
	} finally {
	    if (con != null) {
		try {
		    con.close();
		} catch (SQLException e) {
		    quit(e.getMessage());
		}
	    }
	}
    }  // end go()


    /** Used to output an error message and exit */
    private void quit(String message) {
	System.err.println(message);
	System.exit(1);
    }

} // end class EmployeeSalary


