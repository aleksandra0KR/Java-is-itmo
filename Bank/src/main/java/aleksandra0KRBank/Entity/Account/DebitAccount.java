package aleksandra0KRBank.Entity.Account;

import aleksandra0KRBank.Entity.Transaction.Transfer;
import aleksandra0KRBank.Exceptions.NotEnoughMoneyException;
import aleksandra0KRBank.Tools.Status;
import aleksandra0KRBank.Model.Account.Account;
import aleksandra0KRBank.Entity.User.User;
import aleksandra0KRBank.Entity.Bank.Bank;
import aleksandra0KRBank.Model.Transaction.Transaction;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Calendar;

/**
 * Class for the debit account
 * It extends the Account class and includes methods for managing money transactions and calculations.
 * @author Aleksandra0KR
 * @version 1.0
 */

public class DebitAccount extends Account {
    /**
     * Constructs a DebitAccount object with the specified parameters.
     *
     * @param money the initial amount of money in the account
     * @param openDate the date when the account was opened
     * @param closeDate the date when the account will be closed
     * @param user the user associated with the account
     * @param bank the bank associated with the account
     * @param percentage the year percentage for profit calculation
     */
    public DebitAccount(BigDecimal money, Calendar openDate, Calendar closeDate, User user, Bank bank, BigDecimal percentage) {
        super(money, openDate, closeDate, user, percentage, BigDecimal.ZERO);
       Bank = bank;
    }

    /**
     * Calculates daily percentage based on the number of days.
     *
     * @param days the number of days for which to calculate the daily percentage
     */
    @Override
    public void DailyPercentage(int days) {
        if(Money.compareTo(BigDecimal.ZERO) <= 0) {
            return;
        }

        BigDecimal dailyProfit = Percentage.divide(BigDecimal.valueOf(365), 10, RoundingMode.CEILING).multiply(Money);
        BigDecimal daysProfit = dailyProfit.multiply(BigDecimal.valueOf(days));
        Profit = Profit.add(daysProfit);
    }

    /**
     * Calculates the monthly profit for the account.
     *
     * @return a Transaction object representing the monthly profit operation
     */
    @Override
    public Transaction MonthlyProfit() {
        if (Percentage.equals(BigDecimal.ZERO)) return null;
        Transaction transaction = new Transfer(Bank.Account,this, Profit, Status.Created);
        Profit = BigDecimal.ZERO;
        return transaction;
    }

    /**
     * Calculates the monthly commission for the account. DebitAccount doesn't have commission
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
        if(Money.compareTo(money) < 0) throw new NotEnoughMoneyException();
        Money = Money.subtract(money);
    }
}
