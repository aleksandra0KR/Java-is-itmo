package aleksandra0KR.Entity.Account;

import aleksandra0KR.Model.Account.Account;
import aleksandra0KR.Entity.User.User;
import aleksandra0KR.Entity.Bank.Bank;

import java.math.BigDecimal;
import java.util.Calendar;

public class CreditAccount extends Account {
    private BigDecimal CreditLimit;
    public CreditAccount(BigDecimal money, Calendar openDate, Calendar closeDate, User user, Bank bank, BigDecimal creditLimit, BigDecimal commission){
        super(money, openDate, closeDate, user,BigDecimal.ZERO, commission);
        CreditLimit = creditLimit;
        Bank = bank;
    }
}
