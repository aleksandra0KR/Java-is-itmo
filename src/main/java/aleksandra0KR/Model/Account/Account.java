package aleksandra0KR.Model.Account;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

import aleksandra0KR.Entity.Status.Status;
import aleksandra0KR.Entity.Transaction.Transfer;
import aleksandra0KR.Entity.Transaction.Withdraw;
import aleksandra0KR.Entity.User.User;
import aleksandra0KR.Entity.Bank.Bank;
import aleksandra0KR.Model.Transaction.Transaction;
import lombok.Getter;

public abstract class Account {
    public BigDecimal Profit;
    @Getter
    public UUID AccountId;
    public BigDecimal Money;
    protected Calendar CreationDate;
    public Calendar CloseDate;
    public User User;
    public Bank Bank;
    public BigDecimal Percentage;

    public BigDecimal Commission;


    public Account(BigDecimal money, Calendar creationDate, Calendar closeDate, User user, BigDecimal percentage, BigDecimal commission){
        AccountId = UUID.randomUUID();
        Money = money;
        CreationDate = creationDate;
        CloseDate = closeDate;
        User = user;
        Percentage = percentage;
        Commission = commission;

    }
    public BigDecimal DailyPercentage(){
        if(Money.compareTo(BigDecimal.ZERO) <= 0) {
            return BigDecimal.ZERO;
        }
        BigDecimal dailyProfit = Percentage.divide(BigDecimal.valueOf(365), 10, RoundingMode.CEILING).multiply(Money);
        Profit.add(dailyProfit);
        return dailyProfit;
    }
    public Transaction MonthlyProfit(){
        Transaction transaction = new Withdraw(this,Bank.Account, Profit, Status.Created);
        return transaction;
    }

    public Transaction MonthlyCommission(){
        return (new Transfer(this,Bank.Account, Commission, Status.Created));
    }
}
