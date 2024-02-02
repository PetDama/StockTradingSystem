import model.Stock;

import java.util.ArrayList;
import java.util.List;

public class StockManager {

    private static StockManager instance;
    private List<Stock> stocks;

    private StockManager() {
        stocks = new ArrayList<>();
    }

    public static StockManager getInstance() {
        if (instance == null) {
            instance = new StockManager();
        }
        return instance;
    }

    public void addStock(Stock stock) {
        stocks.add(stock);
    }
}
