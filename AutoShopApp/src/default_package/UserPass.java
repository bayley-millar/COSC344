/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package default_package;

/**
 *
 * @author jbenn
 */

import java.io.*;
import java.util.*;
import java.lang.*;


public class UserPass {

    private String password;
    private String username;


    public UserPass () {
	String line = null;
	String passwordFile = "pass.dat";
	try {
	    BufferedReader inFile = 
		new BufferedReader(new FileReader(passwordFile));
	    // Read the username from the file and store it.
	    if ((line = inFile.readLine()) == null) {
		quit(passwordFile + " is empty");
	    }
	    StringTokenizer tok = new StringTokenizer(line);
	    if (tok.countTokens() != 1) {
		quit("Username line has an error");
	    }
	    username = tok.nextToken();
	    // Read the password from the file and store it.
	    if ((line = inFile.readLine()) == null) {
		quit(passwordFile + " has a bad format");
	    }
	    tok = new StringTokenizer(line);
	    if (tok.countTokens() != 1) {
		quit("Password line has an error");
	    }
	    password = tok.nextToken();
	} catch (FileNotFoundException e) {
	    quit("The file, " + passwordFile + ", was not found.");
	} catch (IOException e) {
	    quit("An error occured trying to read " + passwordFile);
	}
    }

    // Returns the password

    public String getPassWord() {
	return password;
    }

    // Returns the username

    public String getUserName() {
	return username;
    }

    // Used for printing reasons for exceptions or errors.

    private void quit(String message) {
	System.err.println(message);
	System.exit(1);
    }

} 