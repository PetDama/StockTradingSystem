package user;

import java.util.HashMap;
import java.util.Map;

public class Portfolio {

    private Map<String, Integer> ownedStocks;
    private double balance;

    public Portfolio() {
        ownedStocks = new HashMap<>();
        balance = 355000.0; // Inițializăm soldul cu 5000.0
    }

    public void addStock(String ticker, int quantity) {
        ownedStocks.put(ticker, ownedStocks.getOrDefault(ticker, 0) + quantity);
    }

    public void removeStock(String ticker, int quantity) {
        int currentQuantity = ownedStocks.getOrDefault(ticker, 0);
        if (currentQuantity - quantity <= 0) {
            ownedStocks.remove(ticker);
        } else {
            ownedStocks.put(ticker, currentQuantity - quantity);
        }
    }

    public Map<String, Integer> getOwnedStocks() {
        return ownedStocks;
    }

    public void setOwnedStocks(Map<String, Integer> ownedStocks) {
        this.ownedStocks = ownedStocks;
    }

    public double getBalance() {
        return balance;
    }

    public void addBalance(double amount) {
        balance += amount;
    }

    public void deductBalance(double amount) {
        balance -= amount;
    }
}
