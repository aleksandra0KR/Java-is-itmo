package ru.aleksandra0KR.bank.Entity.Transaction;

import ru.aleksandra0KR.bank.Model.Account.Account;
import ru.aleksandra0KR.bank.Tools.Status;
import ru.aleksandra0KR.bank.Model.Transaction.Transaction;
import ru.aleksandra0KR.bank.Tools.CheckingForValidTransaction;
import lombok.AllArgsConstructor;

import java.math.BigDecimal;

import static ru.aleksandra0KR.bank.Tools.Status.Cancelled;
import static ru.aleksandra0KR.bank.Tools.Status.Valid;

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
        Status = Valid;
        Account.getHistoryOfTransactions().add(this);

    }

    /**
     * Cancels the withdrawal transaction by adding back the withdrawn money to the account.
     * Updates the status of the transaction to Cancel.
     */
    @Override
    public void cancel() {

        CheckingForValidTransaction checker = new CheckingForValidTransaction();
        checker.CheckingForValidTransactionForCancel(Account, Money, Status);
        checker.CheckValidationOfTransactionMoney(Account, Money);

        Account.setMoney( Account.getMoney().add(Money));

        Status = Cancelled;
    }

    /**
     Prints information about the withdraw transaction.
     */
    @Override
    public void printInfo() {
        System.out.println("Withdraw from Account: " + Account.getAccountId() + " Money: " + Money + " Status: " + Status);
    }
}
