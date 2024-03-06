package aleksandra0KRBank.Entity.Transaction;

import aleksandra0KRBank.Model.Transaction.Transaction;
import aleksandra0KRBank.Model.Account.Account;
import aleksandra0KRBank.Entity.Status.Status;
import aleksandra0KRBank.Tools.CheckingForValidTransaction;
import lombok.AllArgsConstructor;


import java.math.BigDecimal;
/**
 * Class for Transfer
 * Handles the process of transfer money from one account to another.
 * @author Aleksandra0KR
 * @version 1.0
 */
@AllArgsConstructor
public class Transfer implements Transaction {
    private Account Sender;
    private Account Receiver;
    private BigDecimal Money;
    private Status Status;

    /**
     * Executes the transfer transaction by subtracting money from the sender's account and adding it to the receiver's account.
     * Updates the status of the transaction to Valid and adds the transaction to both sender's and receiver's history.
     */
    @Override
    public void execute() {
        CheckingForValidTransaction checker = new CheckingForValidTransaction();
        checker.CheckValidationOfTransaction(Sender, Receiver, Money, Status);

        Sender.Money = Sender.Money.subtract(Money);
        Receiver.Money =  Receiver.Money.add(Money);
        Status = Status.Valid;
        Sender.HistoryOfTransactions.add(this);
        Receiver.HistoryOfTransactions.add(this);

    }

    /**
     * Cancels the transfer transaction by reversing the money transfer between sender and receiver.
     * Updates the status of the transaction to Cancelled.
     */
    @Override
    public void cancel() {

        CheckingForValidTransaction checker = new CheckingForValidTransaction();
        checker.CheckingForValidTransactionForCancel(Sender, Receiver, Money,Status);

        Receiver.Money = Receiver.Money.subtract(Money);
        Sender.Money = Sender.Money.add(Money);

        Status = Status.Cancelled;
    }

    /**
     * Prints information about the transfer transaction including sender, receiver, amount of money, and status.
     */
    @Override
    public void printInfo() {
        System.out.println("Transaction Sender: " + Sender.AccountId + " Receiver: " + Receiver.AccountId + " Money: " + Money + "Status: " + Status);
    }
}
