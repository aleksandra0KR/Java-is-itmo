package aleksandra0KR.Tools;

import aleksandra0KR.Exceptions.CancelledOperationException;
import aleksandra0KR.Exceptions.NotEnoughMoneyException;
import aleksandra0KR.Exceptions.NullAccountException;
import aleksandra0KR.Exceptions.UntrustedTransactionException;
import aleksandra0KR.Model.Account.Account;
import aleksandra0KR.Entity.Status.Status;

import java.math.BigDecimal;

public class CheckingForValidTransaction {
    public void CheckValidationOfTransaction(Account sender, BigDecimal money, Status status){
        if (status == Status.Cancelled) throw new CancelledOperationException();

        if (sender == null) throw new NullAccountException("Sender");

        if(!sender.User.IsVerified()){
            if(money.compareTo(sender.Bank.UntrustedLimit) > 0) throw new UntrustedTransactionException(money);
        }
    }
    public void CheckValidationOfTransaction(Account sender, Account receiver, BigDecimal money){
        if (sender == null) throw new NullAccountException("Sender");
        if (receiver == null)  throw new NullAccountException("Receiver");

        if(sender.Money.compareTo(money) < 0) throw new NotEnoughMoneyException();
        if(!sender.User.IsVerified()){
            if(money.compareTo(sender.Bank.UntrustedLimit) > 0) throw new UntrustedTransactionException(money);
        }
        if(!receiver.User.IsVerified()){
            if(money.compareTo(receiver.Bank.UntrustedLimit) > 0) throw new UntrustedTransactionException(money);
        }
    }


    public void CheckingForValidTransactionForCancel(Account sender, BigDecimal money, Status status){
        if (status == Status.Cancelled) throw new CancelledOperationException();

        if (sender == null) throw new NullAccountException("Sender");

        if(sender.Money.compareTo(money) < 0) throw new NotEnoughMoneyException();

    }
    public void CheckingForValidTransactionForCancel(Account sender, Account receiver, BigDecimal money, Status status){
        if (status == Status.Cancelled) throw new CancelledOperationException();

        if (sender == null) throw new NullAccountException("Sender");
        if (receiver == null) throw new NullAccountException("Receiver");

        if(receiver.Money.compareTo(money) < 0) throw new NotEnoughMoneyException();

    }

}
