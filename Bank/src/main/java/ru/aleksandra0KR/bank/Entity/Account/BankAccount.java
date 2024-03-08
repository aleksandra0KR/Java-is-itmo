package ru.aleksandra0KR.bank.Entity.Account;

import ru.aleksandra0KR.bank.Exceptions.NotEnoughMoneyException;
import ru.aleksandra0KR.bank.Model.Account.Account;
import ru.aleksandra0KR.bank.Model.Transaction.Transaction;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Class for the bank account
 * It extends the Account class and includes methods for managing money transactions and calculations.
 * @author Aleksandra0KR
 * @version 1.0
 */

public class BankAccount extends Account {

    /**
     * Constructs a BankAccount object with the specified initial money and opening date.
     *
     * @param money the initial amount of money in the account
     * @param openDate the date when the account was opened
     * other parameters is nulls because the bank account does not have an expiration date,
     * a specific user, and commissions as a percentage
     */
    public BankAccount(BigDecimal money, LocalDate openDate) {
        super(money, openDate, null, null, BigDecimal.ZERO, BigDecimal.ZERO);
    }

    /**
     * Calculates daily percentage based on the number of days. BankAccount doesn't have a percentage
     *
     * @param days the number of days for which to calculate the daily percentage
     */

    public void DailyPercentage(int days) {}

    /**
     * Calculates the monthly profit for the account. BankAccount doesn't have a percentage
     *
     * @return a Transaction object representing the monthly profit operation
     */

    public Transaction MonthlyProfit() {
        return null;
    }

    /**
     * Calculates the monthly commission for the account. BankAccount doesn't have a commission
     *
     * @return a Transaction object representing the monthly commission operation
     */
    public Transaction MonthlyCommission() {
        return null;
    }

    /**
     * Takes off a specified amount of money from the account balance.
     *
     * @param money the amount to be subtracted from the account balance
     * @throws NotEnoughMoneyException customized exception if there is not enough money in the account to perform the transaction
     */
    public void TakeOffMoney(BigDecimal money) {
        if(getMoney().compareTo(money) <= 0) throw new NotEnoughMoneyException();
        setMoney(getMoney().subtract(money));
    }
}
