/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

/**
 *
 * @author rstorr
 */
public class Account {
    private String lastVisited;
    private int balance;
    private String id;

    public Account(String lastVisited, int balance, String id) {
        this.lastVisited = lastVisited;
        this.balance = balance;
        this.id = id;
    }

    public String getLastVisited() {
        return lastVisited;
    }

    public void setLastVisited(String lastVisited) {
        this.lastVisited = lastVisited;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return  "lastVisited= " + lastVisited + ", balance= " + balance + ", id= " + id ;
    }


    
    
    
}
