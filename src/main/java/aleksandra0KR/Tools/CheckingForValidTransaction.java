package aleksandra0KR.Tools;

import aleksandra0KR.Model.Account.Account;
import aleksandra0KR.Entity.Status.Status;

import java.math.BigDecimal;
import java.util.MissingFormatArgumentException;

public class CheckingForValidTransaction {
    public void CheckValidationOfTransaction(Account sender, BigDecimal money, Status status){
        if (status == Status.Cancelled) throw new NullPointerException("Cancelled operation can't be cancelled one more time");

        if (sender == null) throw new NullPointerException("Sender can't be null");

        if(!sender.User.IsVerified()){
            if(money.compareTo(sender.Bank.UntrustedLimit) > 0) throw new NullPointerException("operation can't be approved"); // TODO custom exception
        }
    }
    public void CheckValidationOfTransaction(Account sender, Account receiver, BigDecimal money){
        if (sender == null) throw new NullPointerException("Sender can't be null");
        if (receiver == null) throw new NullPointerException("Receiver can't be null");

        if(sender.Money.compareTo(money) < 0) throw new MissingFormatArgumentException("not enough money"); // change exception

        if(!sender.User.IsVerified()){
            if(money.compareTo(sender.Bank.UntrustedLimit) > 0) throw new NullPointerException("operation can't be approved"); // TODO custom exception
        }
        if(!receiver.User.IsVerified()){
            if(money.compareTo(receiver.Bank.UntrustedLimit) > 0) throw new NullPointerException("operation can't be approved"); // TODO custom exception
        }
    }


    public void CheckingForValidTransactionForCancel(Account sender, BigDecimal money, Status status){
        if (status == Status.Cancelled) throw new NullPointerException("Cancelled operation can't be cancelled one more time");

        if (sender == null) throw new NullPointerException("Sender can't be null");

        if(sender.Money.compareTo(money) < 0) throw new MissingFormatArgumentException("not enough money"); // change exception

    }
    public void CheckingForValidTransactionForCancel(Account sender, Account receiver, BigDecimal money, Status status){
        if (status == Status.Cancelled) throw new NullPointerException("Cancelled operation can't be cancelled one more time");

        if (sender == null) throw new NullPointerException("Sender can't be null");
        if (receiver == null) throw new NullPointerException("Receiver can't be null");

        if(receiver.Money.compareTo(money) < 0) throw new MissingFormatArgumentException("not enough money"); // change exception

    }

}
