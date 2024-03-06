package aleksandra0KRBank.Entity.Account;

import aleksandra0KRBank.Exceptions.NotEnoughMoneyException;
import aleksandra0KRBank.Model.Account.Account;
import aleksandra0KRBank.Model.Transaction.Transaction;

import java.math.BigDecimal;
import java.util.Calendar;

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
     * other parameters are nulls because the bank account does not have an expiration date, a specific user, and commissions as a percentage
     */
    public BankAccount(BigDecimal money, Calendar openDate) {
        super(money, openDate, null, null, BigDecimal.ZERO, BigDecimal.ZERO);
    }

    /**
     * Calculates daily percentage based on the number of days. BankAccount doesn't have percentage
     *
     * @param days the number of days for which to calculate the daily percentage
     */
    @Override
    public void DailyPercentage(int days) {}

    /**
     * Calculates the monthly profit for the account. BankAccount doesn't have percentage
     *
     * @return a Transaction object representing the monthly profit operation
     */
    @Override
    public Transaction MonthlyProfit() {
        return null;
    }

    /**
     * Calculates the monthly commission for the account. BankAccount doesn't have commission
     *
     * @return a Transaction object representing the monthly commission operation
     */
    @Override
    public Transaction MonthlyCommission() {
        return null;
    }

    /**
     * Takes off a specified amount of money from the account balance.
     *
     * @param money the amount to be subtracted from the account balance
     * @throws NotEnoughMoneyException customized exception if there is not enough money in the account to perform the transaction
     */
    @Override
    public void TakeOffMoney(BigDecimal money) {
        if(Money.compareTo(money) <= 0) throw new NotEnoughMoneyException();
        Money = Money.subtract(money);
    }
}
