package aleksandra0KR.Entity.Bank;

import aleksandra0KR.Model.Transaction.Transaction;
import aleksandra0KR.Entity.Status.Status;
import aleksandra0KR.Entity.Transaction.Transfer;
import aleksandra0KR.Entity.User.User;
import aleksandra0KR.Model.Account.Account;

import java.math.BigDecimal;
import java.time.Clock;
import java.util.*;

public class Bank {
    public String Name;
    public UUID BankId;
    public Account Account;
    public Calendar Calendar;
    private BigDecimal DebitPercentage;
    private HashMap<BigDecimal, BigDecimal> DepositPercentage;
    private BigDecimal CreditLimit;
    private BigDecimal CreditCommission;
    private List<User> _users = new ArrayList<>();
    private List<Account> _accounts = new ArrayList<>();
    private List<Transaction> _operations = new ArrayList<>();

    public Bank(String name, BigDecimal money, Account account){
        BankId = UUID.randomUUID();
        Name = name;
        Account = account;
    }

    public Account GetUserAccount(User user){
        for(Account account : _accounts){
            if (account.User == user) return account;
        }
        return null;
    }
    public void AddUser(User user){
        if(!_users.contains(user)) _users.add(user);
    }
    public void AddAccount(Account account){
        if(!_accounts.contains(account)) _accounts.add(account);
    }

    public void NotifyNewMonth(){
        for(Account account: _accounts){
            account.MonthlyProfit();
            account.MonthlyCommission();
        }
    }
    public void NotifyNewTime(Calendar calendar){
        if(calendar == null) throw new NullPointerException("calender can't be null");
    }
}
