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
    private int id;
    private int customerId;

    public Account(String lastVisited, int balance, int id, int customerId) {
        this.lastVisited = lastVisited;
        this.balance = balance;
        this.id = id;
        this.customerId = customerId;
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }
    
    
    
}
