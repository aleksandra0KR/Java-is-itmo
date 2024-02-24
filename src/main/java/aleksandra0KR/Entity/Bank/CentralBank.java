package aleksandra0KR.Entity.Bank;

import aleksandra0KR.Entity.Status.Status;
import aleksandra0KR.Entity.Transaction.ReplenishmentDeposit;
import aleksandra0KR.Entity.Transaction.TransactionCaretaker;
import aleksandra0KR.Entity.Transaction.Transfer;
import aleksandra0KR.Entity.Transaction.Withdraw;
import aleksandra0KR.Model.Account.Account;

import java.math.BigDecimal;
import java.util.*;

public class CentralBank {
    // TODO make singleton
    private final List<Bank> _banks;

    private Calendar BankCalendar;
    private final TransactionCaretaker TransactionCaretaker;

    public CentralBank(){
        _banks = new ArrayList<>();
        TransactionCaretaker = new TransactionCaretaker();
    }

    public Bank GetBank(String bankName){
        for(Bank bank: _banks){
            if (Objects.equals(bank.Name, bankName)) return bank;
        }
        throw new NoSuchElementException("There is no bank with name " + bankName);
    }

    public void AddBank(Bank bank){
        if (_banks.contains(bank)) return;
        bank.BankCalendar = BankCalendar;
        _banks.add(bank);
    }

    public void updateClock(Calendar calendar){
        if(calendar == null) throw new NullPointerException("Clock can't be null");
        BankCalendar = calendar;
    }

    public void CheckForNewMoth(){
        if(BankCalendar.get(Calendar.DAY_OF_MONTH) == 1){
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
    public UUID Withdraw(Account sender, Account receiver, BigDecimal amount){
        return TransactionCaretaker.Backup(new Withdraw(sender, receiver, amount, Status.Created));
    }

    public UUID ReplenishmentDeposit(Account sender, BigDecimal amount){
        return TransactionCaretaker.Backup(new ReplenishmentDeposit(sender, amount, Status.Created));
    }

    public void AddTime(int days){
        BankCalendar.add(Calendar.DATE, days);
    }
}
