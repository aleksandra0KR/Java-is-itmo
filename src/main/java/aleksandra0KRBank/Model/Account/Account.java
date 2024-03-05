package aleksandra0KRBank.Model.Account;

import java.math.BigDecimal;
import java.util.*;

import aleksandra0KRBank.Entity.User.User;
import aleksandra0KRBank.Entity.Bank.Bank;
import aleksandra0KRBank.Model.Transaction.Transaction;
import lombok.Getter;

public abstract class Account {
    public BigDecimal Profit;
    @Getter
    public UUID AccountId;
    @Getter
    public BigDecimal Money;
    protected Calendar CreationDate;
    public Calendar CloseDate;
    public User User;
    public Bank Bank;
    public BigDecimal Percentage;

    public BigDecimal Commission;

    public List<Transaction> HistoryOfTransactions;


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
    public abstract void DailyPercentage(int days);
    public abstract Transaction MonthlyProfit();

    public abstract Transaction MonthlyCommission();

    public void FillUpMoney(BigDecimal money){
        if(money.compareTo(BigDecimal.ZERO) <= 0) throw new IllegalArgumentException("Amount of money should be > 0");
        Money = Money.add(money);
    };

    public abstract void TakeOffMoney(BigDecimal money);
}
