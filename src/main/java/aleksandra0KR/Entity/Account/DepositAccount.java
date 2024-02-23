package aleksandra0KR.Entity.Account;

import aleksandra0KR.Model.Account.Account;
import aleksandra0KR.Entity.User.User;
import aleksandra0KR.Entity.Bank.Bank;

import java.math.BigDecimal;
import java.util.Date;

public class DepositAccount extends Account {
    public DepositAccount(BigDecimal money, Date closeDate, User user, Bank bank, BigDecimal percentage) {
        super(money, new Date(), closeDate, user, bank, percentage, BigDecimal.ZERO);
    }
}
