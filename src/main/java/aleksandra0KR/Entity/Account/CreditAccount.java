package aleksandra0KR.Entity.Account;

import aleksandra0KR.Entity.Status.Status;
import aleksandra0KR.Entity.Transaction.Transfer;
import aleksandra0KR.Exceptions.NotEnoughMoneyException;
import aleksandra0KR.Model.Account.Account;
import aleksandra0KR.Entity.User.User;
import aleksandra0KR.Entity.Bank.Bank;
import aleksandra0KR.Model.Transaction.Transaction;

import java.math.BigDecimal;
import java.util.Calendar;

public class CreditAccount extends Account {
    private BigDecimal CreditLimit;
    public CreditAccount(BigDecimal money, Calendar openDate, Calendar closeDate, User user, Bank bank, BigDecimal creditLimit, BigDecimal commission){
        super(money, openDate, closeDate, user,BigDecimal.ZERO, commission);
        CreditLimit = creditLimit;
        Bank = bank;
    }

    @Override
    public void DailyPercentage(int days) {}

    @Override
    public Transaction MonthlyProfit() {
        return null;
    }

    public Transaction MonthlyCommission()
    {
        if(Money.compareTo(BigDecimal.ZERO) < 0) {
            return (new Transfer(this, Bank.Account, Commission, Status.Created));
        }
        return null;
    }

    @Override
    public void TakeOffMoney(BigDecimal money) {
        if(Money.compareTo(CreditLimit) < 0) throw new NotEnoughMoneyException();
        Money = Money.subtract(money);
    }
}
