package ru.aleksandra0KR.bank.Entity.Bank;

import ru.aleksandra0KR.bank.Tools.Status;
import ru.aleksandra0KR.bank.Entity.Transaction.Replenishment;
import ru.aleksandra0KR.bank.Entity.Transaction.TransactionCaretaker;
import ru.aleksandra0KR.bank.Entity.Transaction.Transfer;
import ru.aleksandra0KR.bank.Entity.Transaction.Withdraw;
import ru.aleksandra0KR.bank.Model.Account.Account;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.*;

/**
 * Class for the central bank
 * @author Aleksandra0KR
 * @version 1.0
 */
public class CentralBank {
    private static CentralBank instance;

    /**
     * List of all banks
     */
    private final List<Bank> _banks;

    @Getter
    private Calendar BankCalendar;
    private final ru.aleksandra0KR.bank.Entity.Transaction.TransactionCaretaker TransactionCaretaker;

    /**
     Private constructor to initialize the CentralBank instance with default values.
     */

    private CentralBank(){
        BankCalendar = Calendar.getInstance();
        _banks = new ArrayList<>();
        TransactionCaretaker = new TransactionCaretaker();
    }

    /**
     Get the singleton instance of CentralBank.
     @return The singleton instance of CentralBank.
     */
    public static CentralBank getInstance()
    {
        if (instance == null)
            instance = new CentralBank();
        return instance;
    }

    /**
     Get a specific bank by its name.
     @param bankName The name of the bank to return.
     @return The Bank object, or null if not found.
     */

    public Bank GetBank(String bankName){
        for(Bank bank: _banks){
            if (Objects.equals(bank.Name, bankName)) return bank;
        }
        return null;
    }

    /**
     Adds a new bank to the central bank's list of banks.
     @param bank The Bank object to add.
     */
    public void AddBank(Bank bank){
        if (_banks.contains(bank)) return;
        bank.BankCalendar = BankCalendar;
        _banks.add(bank);
    }

    /**
     Checks for a new month and notify all banks if a new month has started.
     @param oldMonth The previous month value.
     @param oldYear The previous year value.
     */
    public void CheckForNewMoth(int oldMonth, int oldYear){
        int newMonth = BankCalendar.get(Calendar.MONTH);
        int newYear = BankCalendar.get(Calendar.YEAR);
        if(newMonth != oldMonth || newYear != oldYear){
            for(Bank bank : _banks){
                bank.NotifyNewMonth(TransactionCaretaker);
            }
        }
    }

    /**
     Cancel a specific transaction by its UUID identifier.
     @param transactionId The UUID of the transaction to cancel.
     */
    public void CancelTransaction(UUID transactionId){
        TransactionCaretaker.Undo(transactionId);
    }

    /**
     Initiate a transfer transaction between two accounts and backup the transaction.
     @param sender The sender Account object.
     @param receiver The receiver Account object.
     @param amount The amount of money to transfer.
     @return The UUID of the initiated transfer transaction.
     */
    public UUID Transfer(Account sender, Account receiver, BigDecimal amount){
        return TransactionCaretaker.Backup(new Transfer(sender, receiver, amount, Status.Created));
    }

    /**
     Initiate a withdrawal transaction from an account and backup the transaction.
     @param account The Account from which to withdraw funds.
     @param amount The amount of money to withdraw.
     @return The UUID of the initiated withdrawal transaction.
     */
    public UUID Withdraw(Account account, BigDecimal amount){
        return TransactionCaretaker.Backup(new Withdraw(account, amount, Status.Created));
    }

    /**
     Initiate a replenishment transaction to an account and backup the transaction.
     @param sender The Account receiving the deposit.
     @param amount The amount of money to replenish.
     @return The UUID of the initiated replenishment transaction.
     */
    public UUID Replenishment(Account sender, BigDecimal amount){
        return TransactionCaretaker.Backup(new Replenishment(sender, amount, Status.Created));
    }

    /**
     Add a specified number of days to the central bank's calendar and notify banks about the time change.
     @param days The number of days to add to the current date in the calendar.
     */
    public void AddTime(int days){
        int oldMonth = BankCalendar.get(Calendar.MONTH);
        int oldYear = BankCalendar.get(Calendar.YEAR);
        BankCalendar.add(Calendar.DATE, days);
        for(Bank bank : _banks){
            bank.NotifyNewTime(days);
        }
        this.CheckForNewMoth(oldMonth, oldYear);
    }

}
