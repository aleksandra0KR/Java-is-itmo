package aleksandra0KRBank.Entity.Bank;

import aleksandra0KRBank.Entity.Status.Status;
import aleksandra0KRBank.Entity.Transaction.Replenishment;
import aleksandra0KRBank.Entity.Transaction.TransactionCaretaker;
import aleksandra0KRBank.Entity.Transaction.Transfer;
import aleksandra0KRBank.Entity.Transaction.Withdraw;
import aleksandra0KRBank.Model.Account.Account;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.*;

public class CentralBank {
    private static CentralBank instance;
    private final List<Bank> _banks;

    @Getter
    private Calendar BankCalendar;
    private final TransactionCaretaker TransactionCaretaker;

    private CentralBank(){
        BankCalendar = Calendar.getInstance();
        _banks = new ArrayList<>();
        TransactionCaretaker = new TransactionCaretaker();
    }

    public static CentralBank getInstance()
    {
        if (instance == null)
            instance = new CentralBank();
        return instance;
    }

    public Bank GetBank(String bankName){
        for(Bank bank: _banks){
            if (Objects.equals(bank.Name, bankName)) return bank;
        }
        return null;
    }

    public void AddBank(Bank bank){
        if (_banks.contains(bank)) return;
        bank.BankCalendar = BankCalendar;
        _banks.add(bank);
    }

    public void updateClock(Calendar calendar){
        if(calendar == null) throw new IllegalArgumentException("Clock can't be null");
        BankCalendar = calendar;
    }

    public void CheckForNewMoth(int oldMonth, int oldYear){
        int newMonth = BankCalendar.get(Calendar.MONTH);
        int newYear = BankCalendar.get(Calendar.YEAR);
        if(newMonth != oldMonth || newYear != oldYear){
            for(Bank bank : _banks){
                bank.NotifyNewMonth(TransactionCaretaker);
            }
        }
    }

    public void CancelTransaction(UUID transactionId){
        TransactionCaretaker.Undo(transactionId);
    }

    public UUID Transfer(Account sender, Account receiver, BigDecimal amount){
        return TransactionCaretaker.Backup(new Transfer(sender, receiver, amount, Status.Created));
    }
    public UUID Withdraw(Account account, BigDecimal amount){
        return TransactionCaretaker.Backup(new Withdraw(account, amount, Status.Created));
    }

    public UUID ReplenishmentDeposit(Account sender, BigDecimal amount){
        return TransactionCaretaker.Backup(new Replenishment(sender, amount, Status.Created));
    }

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
