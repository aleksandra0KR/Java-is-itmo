package aleksandra0KRBank.Entity.Transaction;

import aleksandra0KRBank.Tools.Status;
import aleksandra0KRBank.Model.Account.Account;
import aleksandra0KRBank.Model.Transaction.Transaction;
import aleksandra0KRBank.Tools.CheckingForValidTransaction;
import lombok.AllArgsConstructor;

import java.math.BigDecimal;


/**
 * Class for Replenishment
 * Handles the process of adding money to an account.
 * @author Aleksandra0KR
 * @version 1.0
 */
@AllArgsConstructor
public class Replenishment implements Transaction {
    private Account Account;
    private BigDecimal Money;
    private Status Status;

    /**
     Executes the replenishment transaction.
     Validates the transaction, adds money to the account, updates status, and logs the transaction.
     */
    @Override
    public void execute() {
        CheckingForValidTransaction checker = new CheckingForValidTransaction();
        checker.CheckValidationOfTransaction(Account, Money, Status);


        Account.FillUpMoney(Money);
        Status = Status.Valid;
        Account.HistoryOfTransactions.add(this);
    }

    /**
     Cancels the replenishment transaction.
     Validates the cancellation, deducts money from the account, and updates status to cancelled.
     */
    @Override
    public void cancel() {

        CheckingForValidTransaction checker = new CheckingForValidTransaction();
        checker.CheckingForValidTransactionForCancel(Account, Money, Status);

        Account.Money = Account.Money.subtract(Money);

        Status = Status.Cancelled;
    }
    /**
     Prints information about the replenishment transaction.
     */
    @Override
    public void printInfo() {
        System.out.println("Replenishment Account: " + Account.AccountId + " Money: " + Money + "Status: " + Status);
    }
}
