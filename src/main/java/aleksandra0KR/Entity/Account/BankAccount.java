package aleksandra0KR.Entity.Account;

import aleksandra0KR.Model.Account.Account;
import aleksandra0KR.Entity.User.User;
import aleksandra0KR.Entity.Bank.Bank;

import java.math.BigDecimal;
import java.util.Date;

public class BankAccount extends Account {
    public BankAccount(BigDecimal money, Date closeDate, Bank bank) {
        super(money, new Date(), closeDate, null, bank, BigDecimal.ZERO, BigDecimal.ZERO);
    }
}
