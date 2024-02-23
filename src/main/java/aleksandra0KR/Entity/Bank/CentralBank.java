package aleksandra0KR.Entity.Bank;

import aleksandra0KR.Model.Account.Account;

import java.time.Clock;
import java.time.LocalDate;
import java.util.*;

public class CentralBank {
    private List<Bank> _banks = new ArrayList<>();

    private Calendar Calendar;

    public Bank GetBank(String bankName){
        for(Bank bank: _banks){
            if (Objects.equals(bank.Name, bankName)) return bank;
        }
        throw new NoSuchElementException("There is no bank with name " + bankName);
    }

    public void AddBank(Bank bank){
        if (_banks.contains(bank)) return;

        _banks.add(bank);
    }

    public void updateClock(Calendar calendar){
        if(calendar == null) throw new NullPointerException("Clock can't be null");
        Calendar = calendar;
    }

    public void CheckForNewMoth(){
        if(Calendar.get(Calendar.DAY_OF_MONTH) == 1){
            for(Bank bank : _banks){
                bank.NotifyNewMonth();
            }
        }
    }
}
