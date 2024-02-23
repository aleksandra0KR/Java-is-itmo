package aleksandra0KR.Entity.Account;

import aleksandra0KR.Model.Account.Account;
import aleksandra0KR.Entity.User.User;
import aleksandra0KR.Entity.Bank.Bank;

import java.math.BigDecimal;
import java.util.Date;

public class CreditAccount extends Account {
    private BigDecimal CreditLimit;
    public CreditAccount(BigDecimal money, Date closeDate, User user, Bank bank, BigDecimal creditLimit, BigDecimal commission){
        super(money, new Date(), closeDate, user, bank,BigDecimal.ZERO, commission);
        CreditLimit = creditLimit;
    }
}
