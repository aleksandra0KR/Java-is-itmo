package aleksandra0KR.Entity.Bank;

import aleksandra0KR.Entity.Account.CreditAccount;
import aleksandra0KR.Entity.Account.DebitAccount;
import aleksandra0KR.Entity.Account.DepositAccount;
import aleksandra0KR.Entity.Transaction.TransactionCaretaker;
import aleksandra0KR.Model.Observer.ObserverUser;
import aleksandra0KR.Model.Observer.SubjectBank;
import aleksandra0KR.Model.Transaction.Transaction;
import aleksandra0KR.Entity.User.User;
import aleksandra0KR.Model.Account.Account;
import aleksandra0KR.Tools.DepositMoneyGapPercentage;
import lombok.Getter;
import lombok.Setter;

import java.lang.reflect.Array;
import java.math.BigDecimal;
import java.util.*;
public class Bank implements SubjectBank {
    public String Name;
    public UUID BankId;
    public Account Account;
    public Calendar BankCalendar;

    @Getter
    @Setter
    public BigDecimal DebitPercentage;

    @Getter
    @Setter
    public List<DepositMoneyGapPercentage> DepositPercentage;

    @Getter
    @Setter
    public BigDecimal CreditLimit;

    @Getter
    @Setter
    public BigDecimal CreditCommission;

    @Getter
    @Setter
    public BigDecimal UntrustedLimit;
    private final TransactionCaretaker transactionCaretaker;
    private final List<User> _users;
    private final List<Account> _accounts;
    private final List<ObserverUser> _observers;

    public Bank(){
        transactionCaretaker = new TransactionCaretaker();
        _users = new ArrayList<>();
        _accounts = new ArrayList<>();
        BankId = UUID.randomUUID();
        _observers = new ArrayList<>();
    }
    public void AddUser(User user){
        if(!_users.contains(user)) _users.add(user);
    }
    public User GetUser(UUID userId){

        for (User user : _users) {
            if (user.getID().equals(userId)) return  user;
        }

        return null;
    }
    public void AddAccount(Account account){
        if(!_accounts.contains(account)) _accounts.add(account);
    }

    public List<Account> GetUsersAccount(UUID userId) {
        var accounts = new ArrayList<Account>();
        for (Account account : _accounts) {
            if (account.User.getID().toString().equals(userId)) accounts.add(account);
        }
        return accounts;
    }

    public Account GetAccount(UUID accountID){
        for (Account account : _accounts) {
            if (account.AccountId.equals(accountID)) return account;
        }
        return null;
    }
    public void NotifyNewMonth(TransactionCaretaker transactionCaretakerCentralBank){
        for(Account account: _accounts){

            Transaction transaction = account.MonthlyProfit();
            if(transaction != null) {
                transactionCaretaker.Backup(transaction);
                transactionCaretakerCentralBank.Backup(transaction);
            }


            transaction = account.MonthlyCommission();
            if(transaction != null) {
                transactionCaretaker.Backup(transaction);
                transactionCaretakerCentralBank.Backup(transaction);
            }
        }
    }
    public void NotifyNewTime(int days){
        BankCalendar.add(Calendar.DATE, days);
        for(Account account : _accounts){
            account.DailyPercentage(days);
        }
    }

    public BigDecimal AccountDepositPercentage(BigDecimal depositMoney){
        BigDecimal percentage = BigDecimal.ZERO;

        for(DepositMoneyGapPercentage gap: DepositPercentage){
            if(gap.SuitedPercentage(depositMoney)) return gap.Percentage;
        }

        return BigDecimal.ZERO;
    }

    public void Attach(ObserverUser observer)
    {
        if(_observers.contains(observer)) return;
        _observers.add(observer);
    }

    public void Detach(ObserverUser observer)
    {
       _observers.remove(observer);
    }

    public void Notify(String updates)
    {
        for (var observer: _observers)
        {
            observer.Update(updates);
        }
    }
    public DebitAccount openDebitAccount(User user, BigDecimal money, int years) {
        Calendar closeDate = BankCalendar;
        closeDate.add(Calendar.YEAR, years);
        DebitAccount debitAccount = new DebitAccount(money, BankCalendar, closeDate, user, this, DebitPercentage);
        this.AddAccount(debitAccount);
        return debitAccount;
    }

    public CreditAccount openCreditAccount(User user, BigDecimal money, int years) {
        Calendar closeDate = BankCalendar;
        closeDate.add(Calendar.YEAR, years);
        CreditAccount creditAccount = new CreditAccount(money, BankCalendar, closeDate, user, this, CreditLimit, CreditCommission);
        this.AddAccount(creditAccount);
        return creditAccount;
    }

    public DepositAccount openDepositAccount(User user, BigDecimal money, int years) {
        Calendar closeDate = BankCalendar;
        closeDate.add(Calendar.YEAR, years);
        DepositAccount depositAccount = new DepositAccount(money, BankCalendar, closeDate, user, this, AccountDepositPercentage(money));
        this.AddAccount(depositAccount);
        return depositAccount;
    }


}
