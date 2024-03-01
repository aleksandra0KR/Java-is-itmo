package aleksandra0KR.Entity.Bank;

import aleksandra0KR.Entity.Status.Status;
import aleksandra0KR.Entity.Transaction.ReplenishmentDeposit;
import aleksandra0KR.Entity.Transaction.TransactionCaretaker;
import aleksandra0KR.Entity.Transaction.Transfer;
import aleksandra0KR.Entity.Transaction.Withdraw;
import aleksandra0KR.Model.Account.Account;
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

    // TODO add creating of Account which trigers bank
}
