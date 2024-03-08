package ru.aleksandra0KR.bank.Entity.Account;

import ru.aleksandra0KR.bank.Entity.Transaction.Transfer;
import ru.aleksandra0KR.bank.Exceptions.NotEnoughMoneyException;
import ru.aleksandra0KR.bank.Tools.Status;
import ru.aleksandra0KR.bank.Model.Account.Account;
import ru.aleksandra0KR.bank.Entity.User.User;
import ru.aleksandra0KR.bank.Entity.Bank.Bank;
import ru.aleksandra0KR.bank.Model.Transaction.Transaction;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Class for the credit account
 * It extends the Account class and includes methods for managing money transactions and calculations.
 * @author Aleksandra0KR
 * @version 1.0
 */
public class CreditAccount extends Account {
    private final BigDecimal CreditLimit;

    /**
     * Constructs a CreditAccount object with the specified parameters.
     *
     * @param money the initial amount of money in the account
     * @param openDate the date when the account was opened
     * @param closeDate the date when the account will be closed
     * @param user the user associated with the account
     * @param bank the bank associated with the account
     * @param creditLimit the maximum credit limit allowed for the account
     * @param commission the commission charged for using the account
     */
    public CreditAccount(BigDecimal money, LocalDate openDate, LocalDate closeDate, User user, Bank bank, BigDecimal creditLimit, BigDecimal commission){
        super(money, openDate, closeDate, user,BigDecimal.ZERO, commission);
        CreditLimit = creditLimit;
        setBank(bank);
    }

    /**
     * Calculates daily percentage based on the number of days. CreditAccount doesn't have a percentage
     *
     * @param days the number of days for which to calculate the daily percentage
     */
    public void DailyPercentage(int days) {}

    /**
     * Calculates the monthly profit for the account. CreditAccount doesn't have a percentage
     *
     * @return a Transaction object representing the monthly profit operation
     */
    public Transaction MonthlyProfit() {
        return null;
    }

    /**
     * Calculates the monthly commission for the account.
     *
     * @return a Transaction object representing the monthly commission operation
     */
    public Transaction MonthlyCommission()
    {
        if(getMoney().compareTo(BigDecimal.ZERO) < 0) {
            return (new Transfer(this, getBank().getAccount(), getCommission(), Status.Created));
        }
        return null;
    }

    /**
     * Takes off a specified amount of money from the account balance.
     *
     * @param money the amount to be subtracted from the account balance
     * @throws NotEnoughMoneyException customized exception if there is not enough money in the account to perform the transaction
     */
    public void TakeOffMoney(BigDecimal money) {
        if(getMoney().compareTo(CreditLimit) < 0) throw new NotEnoughMoneyException();
        setMoney(getMoney().subtract(money));
    }
}
