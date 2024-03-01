package aleksandra0KR.Entity.Account;

import aleksandra0KR.Model.Account.Account;
import aleksandra0KR.Entity.User.User;
import aleksandra0KR.Entity.Bank.Bank;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;

public class DepositAccount extends Account {
    public DepositAccount(BigDecimal money, Calendar openDate, Calendar closeDate, User user, Bank bank, BigDecimal percentage) {
        super(money, openDate, closeDate, user, percentage, BigDecimal.ZERO);
        Bank = bank;
    }
}
