package main;


import dao.DatabaseManager;
import gui.MainMenu;

/**
 * Main class that contains main method to start the program.
 *
 * @author rstorr
 */
public class Main {

    /**
     * Main method.
     * 
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        final DatabaseManager db = new DatabaseManager();
        new MainMenu(db).setVisible(true);
    }
    
}
