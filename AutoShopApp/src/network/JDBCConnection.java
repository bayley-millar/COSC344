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
 * @author reuben
 */
public class JDBCConnection {

    private Connection connection;

    public JDBCConnection() {
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
    
    public PreparedStatement createPreparedStatement(String sql){
        try {
            return connection.prepareStatement(sql);
        } catch (SQLException ex) {
            Logger.getLogger(JDBCConnection.class.getName()).
                    log(Level.SEVERE, null, ex);
            return null;
        }
    }

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

    public void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                quit(e.getMessage());
            }
        }
    }

    private void quit(String message) {
        System.err.println(message);
        System.exit(1);
    }
}
