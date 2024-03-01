package aleksandra0KR.Entity.Account;

import aleksandra0KR.Model.Account.Account;
import aleksandra0KR.Entity.User.User;
import aleksandra0KR.Entity.Bank.Bank;

import java.math.BigDecimal;
import java.util.Calendar;

public class BankAccount extends Account {
    public BankAccount(BigDecimal money, Calendar openDate) {
        super(money, openDate, null, null, BigDecimal.ZERO, BigDecimal.ZERO);
    }
}
