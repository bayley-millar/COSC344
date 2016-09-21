/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import GUI.MainMenu;

/**
 *
 * @author rstorr
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        MainMenu menu = new MainMenu();
        menu.setVisible(true);
        new EmployeeSalary().printSalaries();
    }
    
}
