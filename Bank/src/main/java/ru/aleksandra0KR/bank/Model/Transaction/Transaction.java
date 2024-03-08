package ru.aleksandra0KR.bank.Model.Transaction;

/**
 * Interface for transaction
 * @author Aleksandra0KR
 * @version 1.0
 */
public interface Transaction {
    void execute();
    void cancel();
    void printInfo();
}
