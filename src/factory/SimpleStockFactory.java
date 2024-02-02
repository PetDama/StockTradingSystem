package factory;

import model.Stock;
public class SimpleStockFactory implements StockFactory {
    public Stock createStock(String name, String ticker, double price, int quantity) {
        return new Stock(name, ticker, price, quantity);
    }
}
