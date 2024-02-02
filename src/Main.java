import factory.StockFactory;
import factory.SimpleStockFactory;
import manager.AuthenticationManager;
import manager.UserManager;
import model.Stock;
import transactions.Transaction;
import user.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Main {
    static final Scanner scanner = new Scanner(System.in);
    static final UserManager userManager = new UserManager();
    static final AuthenticationManager authManager = new AuthenticationManager(userManager);
    static final StockFactory stockFactory = new SimpleStockFactory();
    static final List<Stock> stocks = new ArrayList<>();

    public static void main(String[] args) {
        // Creare și adăugare stocuri de exemplu
        stocks.add(stockFactory.createStock("Apple Inc.", "AAPL", 150.0, 10));
        stocks.add(stockFactory.createStock("Microsoft Corporation", "MSFT", 300.0, 15));
        stocks.add(stockFactory.createStock("Google LLC", "GOOGL", 2500.0, 5));
        stocks.add(stockFactory.createStock("Amazon.com Inc.", "AMZN", 3500.0, 20));
        stocks.add(stockFactory.createStock("Facebook Inc.", "FB", 350.0, 25));
        stocks.add(stockFactory.createStock("Tesla Inc.", "TSLA", 700.0, 30));

        boolean isRunning = true;

        while (isRunning) {
            System.out.println("Try our Stock Trading System!");
            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    registerUser();
                    break;
                case 2:
                    User user = login();
                    if (user != null) {
                        performStockTrading(user);
                    }
                    break;
                case 3:
                    isRunning = false;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void registerUser() {
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();
        userManager.registerUser(username, password);
        System.out.println("Registration successful!");
    }

    private static User login() {
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        if (authManager.login(username, password)) {
            User user = userManager.getUser(username);
            System.out.println("Login successful!");
            return user;
        } else {
            System.out.println("Invalid username or password.");
            return null;
        }
    }

    private static void performStockTrading(User user) {
        boolean isTrading = true;

        while (isTrading) {
            System.out.println("\nStock Trading Options:");
            System.out.println("1. Search for Stocks");
            System.out.println("2. Buy Stocks");
            System.out.println("3. Sell Stocks");
            System.out.println("4. Display Portfolio");
            System.out.println("5. Display Transaction History");
            System.out.println("6. Logout");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    searchStocks();
                    break;
                case 2:
                    buyStocks(user);
                    break;
                case 3:
                    sellStocks(user);
                    break;
                case 4:
                    displayPortfolio(user);
                    break;
                case 5:
                    displayTransactionHistory(user);
                    break;
                case 6:
                    isTrading = false;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void searchStocks() {
        System.out.println("Available Stocks:");
        for (Stock stock : stocks) {
            System.out.println(stock.getName() + " (" + stock.getTicker() + ") - Price: $" + stock.getPrice() + " Quantity: " + stock.getQuantity());
        }
    }

    private static void buyStocks(User user) {
        System.out.print("Enter the ticker symbol of the stock you want to buy: ");
        String ticker = scanner.nextLine();
        Stock selectedStock = findStockByTicker(ticker);
        if (selectedStock != null) {
            System.out.print("Enter the quantity of stocks you want to buy: ");
            int quantity = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            double totalPrice = selectedStock.getPrice() * quantity;
            if (user.getPortfolio().getBalance() >= totalPrice) {
                user.getPortfolio().addStock(selectedStock.getTicker(), quantity);
                user.getPortfolio().deductBalance(totalPrice);
                System.out.println("Stock purchased successfully!");

                // Create and add transaction to the user's transaction history
                Transaction transaction = new Transaction(selectedStock.getName(), selectedStock.getTicker(), quantity, selectedStock.getPrice());
                user.addTransaction(transaction);
            } else {
                System.out.println("Insufficient balance to buy the stock.");
            }
        } else {
            System.out.println("Stock with ticker symbol " + ticker + " not found.");
        }
    }

    private static void sellStocks(User user) {
        System.out.print("Enter the ticker symbol of the stock you want to sell: ");
        String ticker = scanner.nextLine();
        Stock selectedStock = findStockByTicker(ticker);
        if (selectedStock != null) {
            System.out.print("Enter the quantity of stocks you want to sell: ");
            int quantity = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            int currentQuantity = user.getPortfolio().getOwnedStocks().getOrDefault(ticker, 0);
            if (currentQuantity >= quantity) {
                user.getPortfolio().removeStock(selectedStock.getTicker(), quantity);
                user.getPortfolio().addBalance(selectedStock.getPrice() * quantity);
                System.out.println("Stock sold successfully!");

                // Create and add transaction to the user's transaction history
                Transaction transaction = new Transaction(selectedStock.getName(), selectedStock.getTicker(), -quantity, selectedStock.getPrice());
                user.addTransaction(transaction);
            } else {
                System.out.println("Insufficient stocks to sell.");
            }
        } else {
            System.out.println("Stock with ticker symbol " + ticker + " not found.");
        }
    }


    private static void displayTransactionHistory(User user) {
        System.out.println("Transaction History:");
        List<Transaction> transactionHistory = user.getTransactionHistory();
        if (!transactionHistory.isEmpty()) {
            for (Transaction transaction : transactionHistory) {
                System.out.println("Stock: " + transaction.getStockName() +
                        " (" + transaction.getStockTicker() + "), " +
                        "Quantity: " + transaction.getQuantity() +
                        ", Price: $" + transaction.getPrice() +
                        ", Date: " + transaction.getDateTime());
            }
        } else {
            System.out.println("No transactions found.");
        }
    }

    private static Stock findStockByTicker(String ticker) {
        for (Stock stock : stocks) {
            if (stock.getTicker().equalsIgnoreCase(ticker)) {
                return stock;
            }
        }
        return null;
    }

    private static void displayPortfolio(User user) {
        System.out.println("Your Portfolio:");
        Map<String, Integer> ownedStocks = user.getPortfolio().getOwnedStocks();
        if(!user.getPortfolio().getOwnedStocks().isEmpty())
        {
            for (Map.Entry<String, Integer> entry : ownedStocks.entrySet()) {
                String ticker = entry.getKey();
                int quantity = entry.getValue();
                Stock stock = findStockByTicker(ticker);
                if (stock != null) {
                    System.out.println(stock.getName() + " (" + ticker + ") - Quantity: " + quantity);
                }
            }
        } else {
            System.out.println("You don't own any stocks.");
        }
    }

}
