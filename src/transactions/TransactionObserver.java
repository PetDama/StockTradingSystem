package transactions;

public interface TransactionObserver {
    void update(Transaction transaction);
}