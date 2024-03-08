package ru.aleksandra0KR.bank.Entity.Account;

import ru.aleksandra0KR.bank.Entity.Transaction.Transfer;
import ru.aleksandra0KR.bank.Exceptions.DepositErrorTakeOffMoney;
import ru.aleksandra0KR.bank.Entity.Bank.CentralBank;
import ru.aleksandra0KR.bank.Tools.Status;
import ru.aleksandra0KR.bank.Exceptions.NotEnoughMoneyException;
import ru.aleksandra0KR.bank.Model.Account.Account;
import ru.aleksandra0KR.bank.Entity.User.User;
import ru.aleksandra0KR.bank.Entity.Bank.Bank;
import ru.aleksandra0KR.bank.Model.Transaction.Transaction;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
/**
 * Class for the deposit account
 * It extends the Account class and includes methods for managing money transactions and calculations.
 * @author Aleksandra0KR
 * @version 1.0
 */
public class DepositAccount extends Account {
    /**
     * Constructs a DepositAccount object with the specified parameters.
     *
     * @param money the initial amount of money in the account
     * @param openDate the date when the account was opened
     * @param closeDate the date when the account will be closed
     * @param user the user associated with the account
     * @param bank the bank associated with the account
     * @param percentage the year percentage for profit calculation
     */
    public DepositAccount(BigDecimal money, LocalDate openDate, LocalDate closeDate, User user, Bank bank, BigDecimal percentage) {
        super(money, openDate, closeDate, user, percentage, BigDecimal.ZERO);
        setBank(bank);
    }

    /**
     * Calculates daily percentage based on the number of days.
     *
     * @param days the number of days for which to calculate the daily percentage
     */
    public void DailyPercentage(int days) {
        if(getMoney().compareTo(BigDecimal.ZERO) <= 0) {
            return;
        }

        BigDecimal dailyProfit = getPercentage().divide(BigDecimal.valueOf(365), 10, RoundingMode.CEILING).multiply(getMoney());
        BigDecimal daysProfit = dailyProfit.multiply(BigDecimal.valueOf(days));
        setProfit(getProfit().add(daysProfit));
    }
    /**
     * Calculates the monthly profit for the account.
     *
     * @return a Transaction object representing the monthly profit operation
     */

    public Transaction MonthlyProfit() {
        if (getPercentage().equals(BigDecimal.ZERO)) return null;
        Transaction transaction = new Transfer(getBank().getAccount(),this, getProfit(), Status.Created);
        setProfit(BigDecimal.ZERO);
        return transaction;
    }

    /**
     * Calculates the monthly commission for the account. Deposit account doesn't have a commission
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
     * @throws DepositErrorTakeOffMoney customized exception if it's not a close date yet
     * @throws NotEnoughMoneyException customized exception if there is not enough money in the account to perform the transaction
     */
    public void TakeOffMoney(BigDecimal money) {
        CentralBank centralBank = CentralBank.getInstance();
        if(this.getCloseDate().isBefore(centralBank.getBankCalendar())) throw new DepositErrorTakeOffMoney();
        if(getMoney().compareTo(money) < 0) throw new NotEnoughMoneyException();
        setMoney(getMoney().subtract(money));
    }
}
