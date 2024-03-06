package aleksandra0KRBank.Entity.Transaction;

import aleksandra0KRBank.Model.Account.Account;
import aleksandra0KRBank.Tools.Status;
import aleksandra0KRBank.Model.Transaction.Transaction;
import aleksandra0KRBank.Tools.CheckingForValidTransaction;
import lombok.AllArgsConstructor;

import java.math.BigDecimal;

/**
 * Class for Withdraw
 * Handles the process of subtracting money from an account.
 * @author Aleksandra0KR
 * @version 1.0
 */
@AllArgsConstructor
public class Withdraw implements Transaction {
    private Account Account;
    private BigDecimal Money;
    private Status Status;

    /**
     * Executes the withdrawal transaction by subtracting money from the account.
     * Updates the status of the transaction to Valid and adds the transaction to the account's history.
     */
    @Override
    public void execute() {
        CheckingForValidTransaction checker = new CheckingForValidTransaction();
        checker.CheckValidationOfTransaction(Account, Money, Status);
        Account.TakeOffMoney(Money);
        Status = Status.Valid;
        Account.HistoryOfTransactions.add(this);

    }

    /**
     * Cancels the withdrawal transaction by adding back the withdrawn money to the account.
     * Updates the status of the transaction to Cancelled.
     */
    @Override
    public void cancel() {

        CheckingForValidTransaction checker = new CheckingForValidTransaction();
        checker.CheckingForValidTransactionForCancel(Account, Money, Status);

        Account.Money = Account.Money.add(Money);

        Status = Status.Cancelled;
    }

    /**
     Prints information about the withdraw transaction.
     */
    @Override
    public void printInfo() {
        System.out.println("Withdraw from Account: " + Account.AccountId + " Money: " + Money + "Status: " + Status);
    }
}
