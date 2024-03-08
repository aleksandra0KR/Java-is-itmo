package ru.aleksandra0KR.bank.Entity.Bank;

import ru.aleksandra0KR.bank.Entity.Account.CreditAccount;
import ru.aleksandra0KR.bank.Entity.Account.DebitAccount;
import ru.aleksandra0KR.bank.Entity.Account.DepositAccount;
import ru.aleksandra0KR.bank.Entity.Transaction.TransactionCaretaker;
import ru.aleksandra0KR.bank.Entity.User.User;
import ru.aleksandra0KR.bank.Model.Observer.ObserverUser;
import ru.aleksandra0KR.bank.Model.Observer.SubjectBank;
import ru.aleksandra0KR.bank.Model.Transaction.Transaction;
import ru.aleksandra0KR.bank.Model.Account.Account;
import ru.aleksandra0KR.bank.Tools.DepositMoneyGapPercentage;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.*;
/**
 * Class for the bank
 * @author Aleksandra0KR
 * @version 1.0
 */
public class Bank implements SubjectBank {
    /**
     * Name of the bank
     */
    public String Name;
    /**
     * Id of the bank
     */
    public UUID BankId;
    /**
     * Account of the bank
     */
    public Account Account;
    /**
     * Today's calendar of the bank
     */
    public Calendar BankCalendar;

    /**
     * Debit Percentage of the bank
     */
    @Getter
    @Setter
    public BigDecimal DebitPercentage;

    /**
     * Deposit Percentage of the bank
     */
    @Getter
    @Setter
    public List<DepositMoneyGapPercentage> DepositPercentage;

    /**
     * Credit Limit of the bank
     */
    @Getter
    @Setter
    public BigDecimal CreditLimit;

    /**
     * Credit Commission of the bank
     */
    @Getter
    @Setter
    public BigDecimal CreditCommission;

    /**
     * Limit of money of the bank for operations with users without passport of address
     */
    @Getter
    @Setter
    public BigDecimal UntrustedLimit;
    /**
     * Users of the bank
     */
    private final List<User> _users;
    /**
     * Accounts of the bank
     */
    private final List<Account> _accounts;
    /**
     * Observers of the bank who will get notifications
     */
    private final List<ObserverUser> _observers;

    /**
     * Creates empty bank
     */
    public Bank(){
        _users = new ArrayList<>();
        _accounts = new ArrayList<>();
        BankId = UUID.randomUUID();
        _observers = new ArrayList<>();
    }
    /**
     * Adds a user to the bank's list of users if not already present.
     *
     * @param user the user to be added
     */
    public void AddUser(User user){
        if(!_users.contains(user)) _users.add(user);
    }
    /**
     * Returns a user based on their unique ID.
     *
     * @param userId the unique ID of the user
     * @return the user object if found, otherwise null
     */
    public User GetUser(UUID userId){

        for (User user : _users) {
            if (user.getID().equals(userId)) return  user;
        }

        return null;
    }
    /**
     * Adds a account to the bank's list of accounts if not already present.
     *
     * @param account the account to be added
     */
    public void AddAccount(Account account){
        if(!_accounts.contains(account)) _accounts.add(account);
    }

    /**
     * Returns a user's accounts based on their unique ID.
     *
     * @param userId the unique ID of the user
     * @return a list of Accounts the user's accounts if found, otherwise null
     */
    public List<Account> GetUsersAccount(UUID userId) {
        var accounts = new ArrayList<Account>();
        for (Account account : _accounts) {
            if (account.User.getID().toString().equals(userId)) accounts.add(account);
        }
        return accounts;
    }

    /**
     * Returns an account based on its unique ID.
     *
     * @param accountID the unique ID of the account
     * @return the account object if found, otherwise null
     */
    public Account GetAccount(UUID accountID){
        for (Account account : _accounts) {
            if (account.AccountId.equals(accountID)) return account;
        }
        return null;
    }
    /**
     * Notifies about a new month by processing monthly profit and commission for all accounts.
     *
     * @param transactionCaretakerCentralBank the caretaker for handling transactions
     */
    public void NotifyNewMonth(TransactionCaretaker transactionCaretakerCentralBank){
        for(Account account: _accounts){

            Transaction transaction = account.MonthlyProfit();
            if(transaction != null) {
                transactionCaretakerCentralBank.Backup(transaction);
            }

            transaction = account.MonthlyCommission();
            if(transaction != null) {
                transactionCaretakerCentralBank.Backup(transaction);
            }
        }
    }
    /**
     * Notifies about a time change by updating the bank's calendar and calculating daily percentages for all accounts.
     *
     * @param days the number of days to added in time
     */
    public void NotifyNewTime(int days){
        BankCalendar.add(Calendar.DATE, days);
        for(Account account : _accounts){
            account.DailyPercentage(days);
        }
    }

    /**
     * Calculates the deposit percentage based on the deposited amount.
     *
     * @param depositMoney the amount of money deposited
     * @return the applicable deposit percentage or zero if not found
     */
    public BigDecimal AccountDepositPercentage(BigDecimal depositMoney){
        BigDecimal percentage = BigDecimal.ZERO;

        for(DepositMoneyGapPercentage gap: DepositPercentage){
            if(gap.SuitedPercentage(depositMoney)) return gap.Percentage;
        }

        return BigDecimal.ZERO;
    }

    /**
     * Attaches an observer to receive notifications from the bank.
     *
     * @param observer the observer to be attached
     */
    public void Attach(ObserverUser observer)
    {
        if(_observers.contains(observer)) return;
        _observers.add(observer);
    }

    /**
     * Detaches an observer from receiving notifications from the bank.
     *
     * @param observer the observer to be detached
     */
    public void Detach(ObserverUser observer)
    {
       _observers.remove(observer);
    }

    /**
     * Notifies all attached observers with specific updates.
     *
     * @param updates information to be sent to observers
     */
    public void Notify(String updates)
    {
        for (var observer: _observers)
        {
            observer.Update(updates);
        }
    }

    /**
     * Opens a new debit account for a user with specified initial money and duration in years.
     *
     * @param user the user opening the account
     * @param money initial amount of money in the account
     * @param years duration in years until account closure
     * @return the newly opened debit account object
     */
    public DebitAccount openDebitAccount(User user, BigDecimal money, int years) {
        Calendar closeDate = BankCalendar;
        closeDate.add(Calendar.YEAR, years);
        DebitAccount debitAccount = new DebitAccount(money, BankCalendar, closeDate, user, this, DebitPercentage);
        this.AddAccount(debitAccount);
        return debitAccount;
    }

    /**
     * Opens a new credit account for a user with specified initial money, duration in years, credit limit, and commission.
     *
     * @param user the user opening the account
     * @param money initial amount of money in the account
     * @param years duration in years until account closure
     * @return the newly opened credit account object
     */

    public CreditAccount openCreditAccount(User user, BigDecimal money, int years) {
        Calendar closeDate = BankCalendar;
        closeDate.add(Calendar.YEAR, years);
        CreditAccount creditAccount = new CreditAccount(money, BankCalendar, closeDate, user, this, CreditLimit, CreditCommission);
        this.AddAccount(creditAccount);
        return creditAccount;
    }

    /**
     * Opens a new deposit account for a user with specified initial money, duration in years,
     * and calculates appropriate deposit percentage based on deposited amount.
     *
     * @param user the user opening the account
     * @param money initial amount of money in the account
     * @param years duration in years until account closure
     * @return the newly opened deposit account object
     */
    public DepositAccount openDepositAccount(User user, BigDecimal money, int years) {
        Calendar closeDate = BankCalendar;
        closeDate.add(Calendar.YEAR, years);
        DepositAccount depositAccount = new DepositAccount(money, BankCalendar, closeDate, user, this, AccountDepositPercentage(money));
        this.AddAccount(depositAccount);
        return depositAccount;
    }

}
