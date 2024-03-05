package aleksandra0KRBank.Entity.Account;

import aleksandra0KRBank.Exceptions.NotEnoughMoneyException;
import aleksandra0KRBank.Model.Account.Account;
import aleksandra0KRBank.Model.Transaction.Transaction;

import java.math.BigDecimal;
import java.util.Calendar;

public class BankAccount extends Account {
    public BankAccount(BigDecimal money, Calendar openDate) {
        super(money, openDate, null, null, BigDecimal.ZERO, BigDecimal.ZERO);
    }

    @Override
    public void DailyPercentage(int days) {}

    @Override
    public Transaction MonthlyProfit() {
        return null;
    }

    @Override
    public Transaction MonthlyCommission() {
        return null;
    }

    @Override
    public void TakeOffMoney(BigDecimal money) {
        if(Money.compareTo(money) <= 0) throw new NotEnoughMoneyException();
        Money = Money.subtract(money);
    }
}
