package factory;

import model.Stock;
public interface StockFactory {
    Stock createStock(String name, String ticker, double price, int quantity);
}
