package ru.aleksandra0KR.bank.Model.Account;

import java.math.BigDecimal;
import java.util.*;

import ru.aleksandra0KR.bank.Model.Transaction.Transaction;
import ru.aleksandra0KR.bank.Entity.User.User;
import lombok.Getter;

/**
 * Abstract Class for the user
 * @author Aleksandra0KR
 * @version 1.0
 */
public abstract class Account {
    public BigDecimal Profit; // The profit of the account.
    @Getter
    public UUID AccountId; // The unique identifier for the account.
    @Getter
    public BigDecimal Money; // The money in the account.
    protected Calendar CreationDate; // The creation date of the account.
    public Calendar CloseDate; // The close date of the account.

    public ru.aleksandra0KR.bank.Entity.User.User User; // The user associated with the account.

    public ru.aleksandra0KR.bank.Entity.Bank.Bank Bank; // The bank associated with the account.
    public BigDecimal Percentage; // The percentage of the account.
    public BigDecimal Commission; // The commission of the account.
    public List<Transaction> HistoryOfTransactions; // The history of transactions for the account.

    /**
     Constructor for the account.
     @param money The initial amount of money in the account.
     @param creationDate The creation date of the account.
     @param closeDate The close date of the account.
     @param user The user associated with the account.
     @param percentage The percentage of the account.
     @param commission The commission of the account.
     */
    public Account(BigDecimal money, Calendar creationDate, Calendar closeDate, User user, BigDecimal percentage, BigDecimal commission){
        AccountId = UUID.randomUUID();
        Money = money;
        CreationDate = creationDate;
        CloseDate = closeDate;
        User = user;
        Percentage = percentage;
        Commission = commission;
        Profit = BigDecimal.ZERO;
        HistoryOfTransactions = new ArrayList<>();

    }

    /**
     Calculates the daily percentage for the account.
     @param days The number of days to calculate the daily percentage for.
     */
    public abstract void DailyPercentage(int days);

    /**
     Calculates the monthly profit for the account.
     @return transaction with the monthly profit of the account.
     */
    public abstract Transaction MonthlyProfit();

    /**
     Calculates the monthly commission for the account.
     @return transaction with the monthly commission of the account.
     */
    public abstract Transaction MonthlyCommission();

    /**
     Fills up the money in the account.
     @param money The amount of money to fill up.
     */
    public void FillUpMoney(BigDecimal money){
        if(money.compareTo(BigDecimal.ZERO) <= 0) throw new IllegalArgumentException("Amount of money should be > 0");
        Money = Money.add(money);
    }

    /**
     Takes off the money from the account.
     @param money The amount of money to take off.
     */
    public abstract void TakeOffMoney(BigDecimal money);
}
