package transactions;

import java.time.LocalDateTime;

public class Transaction {
    private String stockName;
    private String stockTicker;
    private int quantity;
    private double price;
    private LocalDateTime dateTime;

    public Transaction(String stockName, String stockTicker, int quantity, double price) {
        this.stockName = stockName;
        this.stockTicker = stockTicker;
        this.quantity = quantity;
        this.price = price;
        this.dateTime = LocalDateTime.now();
    }

    public String getStockName() {
        return stockName;
    }

    public String getStockTicker() {
        return stockTicker;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getPrice() {
        return price;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    // Metoda toString pentru afișarea detaliilor tranzacției
    @Override
    public String toString() {
        return "Transaction{" +
                "stockName='" + stockName + '\'' +
                ", stockTicker='" + stockTicker + '\'' +
                ", quantity=" + quantity +
                ", price=" + price +
                ", dateTime=" + dateTime +
                '}';
    }
}
