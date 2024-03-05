package aleksandra0KR.Entity.Account;

import aleksandra0KR.Entity.Status.Status;
import aleksandra0KR.Entity.Transaction.Transfer;
import aleksandra0KR.Entity.Transaction.Withdraw;
import aleksandra0KR.Exceptions.NotEnoughMoneyException;
import aleksandra0KR.Model.Account.Account;
import aleksandra0KR.Entity.User.User;
import aleksandra0KR.Entity.Bank.Bank;
import aleksandra0KR.Model.Transaction.Transaction;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Calendar;
import java.util.Date;

public class DebitAccount extends Account {
    public DebitAccount(BigDecimal money, Calendar openDate, Calendar closeDate, User user, Bank bank, BigDecimal percentage) {
        super(money, openDate, closeDate, user, percentage, BigDecimal.ZERO);
        Bank = bank;
    }

    @Override
    public void DailyPercentage(int days) {
        if(Money.compareTo(BigDecimal.ZERO) <= 0) {
            return;
        }

        BigDecimal dailyProfit = Percentage.divide(BigDecimal.valueOf(365), 10, RoundingMode.CEILING).multiply(Money);
        BigDecimal daysProfit = dailyProfit.multiply(BigDecimal.valueOf(days));
        Profit = Profit.add(daysProfit);
    }

    @Override
    public Transaction MonthlyProfit() {
        if (Percentage.equals(BigDecimal.ZERO)) return null;
        Transaction transaction = new Transfer(this,Bank.Account, Profit, Status.Created);
        Profit = BigDecimal.ZERO;
        return transaction;
    }

    @Override
    public Transaction MonthlyCommission() {
        return null;
    }

    @Override
    public void TakeOffMoney(BigDecimal money) {
        if(Money.compareTo(money) < 0) throw new NotEnoughMoneyException();
        Money = Money.subtract(money);
    }
}
