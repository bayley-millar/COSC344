/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author jbenn
 */
public class EmployeeData implements Comparable<EmployeeData> {

    private String firstName = null;
    private String lastName  = null;
    private int    salary    = 0;

    /**
     * Construct a table entry with the result's name and salary
     * 
     * @param firstName  the employee's first name
     * @param lastName   the employee's last name
     * @param salary     the salary for the employee
     */
    public EmployeeData(String firstName, String lastName, int salary) {
	this.firstName  = firstName;
	this.lastName   = lastName;
	this.salary     = salary;
    }


    /**
     * Compares 2 objects, compares based on the employees' salary
     *
     * @param  other is the other EmployeeData to be compared to
     *
     * @return -1 if (this < other); 0 if (this == other); 1 if (this > other)
     */
    public int compareTo(EmployeeData other) {
	if (this.salary < other.salary) {
	    return -1;
	}

	if (this.salary > other.salary) {
	    return 1;
	}
	
	return 0;
    }


    /**
     * Return a String representation of the table Entry
     *
     * @return a String representation, giving employee name and salary
     */
    public String toString() {
	return firstName + "  " + lastName + "  " + salary;
    }


} // class EmployeeData

