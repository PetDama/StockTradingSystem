package user;

import transactions.Transaction;
import transactions.TransactionObserver;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class User {
    private UUID id;
    private String username;
    private String password;
    private Portfolio portfolio;
    private List<Transaction> transactionHistory;
    private List<TransactionObserver> observers;

    public User(String username, String password) {
        this.id = UUID.randomUUID();
        this.username = username;
        this.password = password;
        this.portfolio = new Portfolio();
        this.transactionHistory = new ArrayList<>();
        this.observers = new ArrayList<>();
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Portfolio getPortfolio() {
        return portfolio;
    }

    public void setPortfolio(Portfolio portfolio) {
        this.portfolio = portfolio;
    }

    public List<Transaction> getTransactionHistory() {
        return transactionHistory;
    }

    public void addTransaction(Transaction transaction) {
        transactionHistory.add(transaction);
    }

    public void addObserver(TransactionObserver observer) {
        observers.add(observer);
    }

    public void removeObserver(TransactionObserver observer) {
        observers.remove(observer);
    }

    private void notifyObservers(Transaction transaction) {
        for (TransactionObserver observer : observers) {
            observer.update(transaction);
        }
    }
}
