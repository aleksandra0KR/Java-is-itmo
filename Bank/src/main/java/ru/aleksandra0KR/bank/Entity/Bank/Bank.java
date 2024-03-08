package ru.aleksandra0KR.bank.Entity.Bank;

import ru.aleksandra0KR.bank.Entity.Account.*;
import ru.aleksandra0KR.bank.Entity.Transaction.Replenishment;
import ru.aleksandra0KR.bank.Entity.Transaction.TransactionCaretaker;
import ru.aleksandra0KR.bank.Entity.Transaction.Transfer;
import ru.aleksandra0KR.bank.Entity.Transaction.Withdraw;
import ru.aleksandra0KR.bank.Entity.User.User;
import ru.aleksandra0KR.bank.Model.Observer.SubjectBank;
import ru.aleksandra0KR.bank.Model.Transaction.Transaction;
import ru.aleksandra0KR.bank.Model.Account.Account;
import ru.aleksandra0KR.bank.Tools.DepositMoneyGapPercentage;
import lombok.Getter;
import lombok.Setter;
import ru.aleksandra0KR.bank.Tools.Status;

import java.math.BigDecimal;
import java.time.LocalDate;
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
    @Getter
    @Setter
    private String Name;

    /**
     * ID of the bank
     */
    @Getter
    private final UUID BankId;

    /**
     * Account of the bank
     */
    @Getter
    @Setter
    private Account Account;

    /**
     * Today's calendar of the bank
     */
    @Getter
    @Setter
    private LocalDate BankCalendar;


    /**
     * Debit Percentage of the bank
     */
    @Getter
    @Setter
    private BigDecimal DebitPercentage;

    /**
     * Deposit Percentage of the bank
     */
    @Getter
    @Setter
    private List<DepositMoneyGapPercentage> DepositPercentage;

    /**
     * Credit Limit of the bank
     */
    @Getter
    @Setter
    private BigDecimal CreditLimit;

    /**
     * Credit Commission of the bank
     */
    @Getter
    @Setter
    private BigDecimal CreditCommission;

    /**
     * Limit of money of the bank for operations with users without passport of address
     */
    @Getter
    @Setter
    private BigDecimal UntrustedLimit;

    private final TransactionCaretaker TransactionCaretakerBank;

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
    private final List<User> _observers;

    /**
     * Creates an empty bank
     */
    public Bank(){
        _users = new ArrayList<>();
        _accounts = new ArrayList<>();
        BankId = UUID.randomUUID();
        _observers = new ArrayList<>();
        TransactionCaretakerBank = new TransactionCaretaker();
    }

    /**
     * Adds a user to the bank's list of users if not already present.
     *
     * @param user the user to be added
     */
    public void AddUser(User user){
        if(_users.stream().noneMatch(u -> u.getID().compareTo(user.getID()) == 0)) _users.add(user);
    }

    /**
     * Returns a user based on their unique ID.
     *
     * @param userId the unique ID of the user
     * @return the user object if found, otherwise null
     */
    public User GetUser(UUID userId){

        for (User user : _users) {
            if (user.getID().compareTo(userId) == 0) return  user;
        }

        return null;
    }

    /**
     * Adds an account to the bank's list of accounts if not already present.
     *
     * @param account the account to be added
     */
    public void AddAccount(Account account){
        if(_accounts.stream().noneMatch(a -> a.getAccountId().compareTo(account.getAccountId()) == 0)) _accounts.add(account);
    }

    /**
     * Returns an account based on its unique ID.
     *
     * @param accountID the unique ID of the account
     * @return the account object if found, otherwise null
     */
    public Account GetAccount(UUID accountID){
        for (Account account : _accounts) {
            if (account.getAccountId().compareTo(accountID) == 0) return account;
        }
        return null;
    }

    /**
     * Notifies about a new month by processing monthly profit and commission for all accounts.
     *
     */
    public void NotifyNewMonth(){
        for(Account account: _accounts){

            Transaction transaction = account.MonthlyProfit();
            if(transaction != null) {
                TransactionCaretakerBank.Backup(transaction);
            }

            transaction = account.MonthlyCommission();
            if(transaction != null) {
                TransactionCaretakerBank.Backup(transaction);
            }
        }
    }
    /**
     * Notifies about a time change by updating the bank's calendar and calculating daily percentages for all accounts.
     *
     * @param days the number of days to add in time
     */
    public void NotifyNewTime(int days){
        BankCalendar =  BankCalendar.plusDays(days);
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
    public void Attach(User observer)
    {
        if(_observers.stream().noneMatch(u -> u.getID().compareTo(observer.getID()) == 0)) _observers.add(observer);
    }

    /**
     * Detaches an observer from receiving notifications from the bank.
     *
     * @param observer the observer to be detached
     */
    public void Detach(User observer)
    {

        _observers.removeIf(o ->o.getID().compareTo(observer.getID()) == 0);
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
     * Opens a new debit account for a user with specified initial money and duration in the past years.
     *
     * @param user the user opening the account
     * @param money initial amount of money in the account
     * @param years duration in years until account closure
     * @return the newly opened debit account object
     */
    public DebitAccount openDebitAccount(User user, BigDecimal money, int years) {
        LocalDate closeDate = BankCalendar;
        closeDate = closeDate.plusYears(years);
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
        LocalDate closeDate = BankCalendar;
        closeDate = closeDate.plusYears(years);
        CreditAccount creditAccount = new CreditAccount(money, BankCalendar, closeDate, user, this, CreditLimit, CreditCommission);
        this.AddAccount(creditAccount);
        return creditAccount;
    }

    /**
     * Opens a new deposit account for a user with specified initial money, duration in years,
     * and calculates the appropriate deposit percentage based on deposited amount.
     *
     * @param user the user opening the account
     * @param money initial amount of money in the account
     * @param years duration in years until account closure
     * @return the newly opened deposit account object
     */
    public DepositAccount openDepositAccount(User user, BigDecimal money, int years) {
        LocalDate closeDate = BankCalendar;
        closeDate = closeDate.plusYears(years);
        DepositAccount depositAccount = new DepositAccount(money, BankCalendar, closeDate, user, this, AccountDepositPercentage(money));
        this.AddAccount(depositAccount);
        return depositAccount;
    }

    /**
     Initiate a withdrawal transaction from an account and back up the transaction.
     @param account The Account from which to withdraw funds.
     @param amount The amount of money to withdraw.
     @return The UUID of the initiated withdrawal transaction.
     */
    public UUID Withdraw(Account account, BigDecimal amount){
        return TransactionCaretakerBank.Backup(new Withdraw(account, amount, Status.Created));
    }

    /**
     Initiate a replenishment transaction to an account and back up the transaction.
     @param sender The Account receiving the deposit.
     @param amount The amount of money to replenish.
     @return The UUID of the initiated replenishment transaction.
     */
    public UUID Replenishment(Account sender, BigDecimal amount){
        return TransactionCaretakerBank.Backup(new Replenishment(sender, amount, Status.Created));
    }

    /**
     Initiate a transfer transaction between two accounts and back up the transaction.
     @param sender The sender Account object.
     @param receiver The receiver Account object.
     @param amount The amount of money to transfer.
     @return The UUID of the initiated transfer transaction.
     */
    public UUID Transfer(Account sender, Account receiver, BigDecimal amount){
        return TransactionCaretakerBank.Backup(new Transfer(sender, receiver, amount, Status.Created));
    }

    /**
     Cancels a transaction by undoing it using the TransactionCaretakerBank.
     @param transactionId The unique identifier of the transaction to be canceled.
     */
    public void CancelTransaction(UUID transactionId){

        TransactionCaretakerBank.Undo(transactionId);
    }

}
