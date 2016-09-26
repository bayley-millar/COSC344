package network;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * This class is used to make the DB connect work.
 * We made this as a easier way of connecting to the DB
 * for the database management class.
 */
public class JDBCConnection {

    private Connection connection;
    
    /**
     * Constructor for JDBCConnection, server credentials and 
     * connection object created.
     **/
    public JDBCConnection() {
        /**************************************** 
        Add your server details here to login
        *****************************************
        */ 
        final String user = "bmillar";
        final String pass = "bayleyMillar";
        final String host = "silver";
        final String url = "jdbc:oracle:thin:@" + host + ":1527:cosc344";

        try {
            DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
            connection = DriverManager.getConnection(url, user, pass);

        } catch (SQLException e) {
            quit(e.getMessage());
        }
    }
    
    /**
     * @param String sql, an SQL statement
     * @return PreparedStatement or NULL.
     */
    public PreparedStatement createPreparedStatement(String sql){
        try {
            return connection.prepareStatement(sql);
        } catch (SQLException ex) {
            Logger.getLogger(JDBCConnection.class.getName()).
                    log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    /**
     * This method execute an SQL Query.
     * @param String sql, an SQL statement
     * @return ResultSet or NULL.
     */
    public ResultSet executeQuerySQL(final String sql) {
        Statement stmt;
        try {
            stmt = connection.createStatement();
            return stmt.executeQuery(sql);
        } catch (SQLException ex) {
            Logger.getLogger(JDBCConnection.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
        return null;
    }
    /**
     * This method closed the connection to the database.
     */
    public void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                quit(e.getMessage());
            }
        }
    }
    /**
     * This method prints an error message if connections or method calls
     * fail.
     */
    private void quit(String message) {
        System.err.println(message);
        System.exit(1);
    }
}
