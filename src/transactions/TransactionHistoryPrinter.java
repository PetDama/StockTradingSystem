package transactions;

public class TransactionHistoryPrinter implements TransactionObserver {
    @Override
    public void update(Transaction transaction) {
        System.out.println("Transaction added to history: " + transaction);
    }
}