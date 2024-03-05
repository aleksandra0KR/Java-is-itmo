package aleksandra0KRBank.Entity.Account;

import aleksandra0KRBank.Entity.Status.Status;
import aleksandra0KRBank.Entity.Transaction.Transfer;
import aleksandra0KRBank.Exceptions.NotEnoughMoneyException;
import aleksandra0KRBank.Model.Account.Account;
import aleksandra0KRBank.Entity.User.User;
import aleksandra0KRBank.Entity.Bank.Bank;
import aleksandra0KRBank.Model.Transaction.Transaction;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Calendar;

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
        Transaction transaction = new Transfer(Bank.Account,this, Profit, Status.Created);
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
