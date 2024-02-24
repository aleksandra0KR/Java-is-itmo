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
    public User GetUser(String name, String surname){

        for (User user : _users) {
            if (user.getName().equals(name) && user.getSurname().equals(surname)) return  user;
        }

        return null;
    }
    public void AddAccount(Account account){
        if(!_accounts.contains(account)) _accounts.add(account);
    }

    public void CreateCreditAccount(BigDecimal money, User user,  Date closeDate){
        Account creditAccount = new CreditAccount(money, closeDate, user, this, CreditLimit, CreditCommission);
        this.AddAccount(creditAccount);
    }

    public void CreateDebitAccount(BigDecimal money, User user, Date closeDate){
        Account debitAccount = new DebitAccount(money, closeDate, user, this, DebitPercentage);
        this.AddAccount(debitAccount);
    }

    public void CreateDeposit(BigDecimal money, User user, Date closeDate){
        Account depositAccount = new DepositAccount(money, closeDate, user, this, this.AccountDepositPercentage(money));
        this.AddAccount(depositAccount);
    }

    public Account getAccount(UUID accountId) {
        for (Account account : _accounts) {
            if (account.getAccountId().toString().equals(accountId)) return account;
        }

        return null;
    }
    public void NotifyNewMonth(TransactionCaretaker transactionCaretakerCentralBank){
        for(Account account: _accounts){

            Transaction transaction = account.MonthlyProfit();
            transactionCaretaker.Backup(transaction);
            transactionCaretakerCentralBank.Backup(transaction);

            transaction = account.MonthlyCommission();
            transactionCaretaker.Backup(transaction);
            transactionCaretakerCentralBank.Backup(transaction);
        }
    }
    public void NotifyNewTime(Calendar calendar){
        if(calendar == null) throw new NullPointerException("calender can't be null");
        BankCalendar = calendar;
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

    // Запуск обновления в каждом подписчике.
    public void Notify(String updates)
    {

        for (var observer: _observers)
        {
            observer.Update(updates);
        }
    }


}
