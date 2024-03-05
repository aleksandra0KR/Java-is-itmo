package aleksandra0KRBank.Tools;

import aleksandra0KRBank.Entity.Account.BankAccount;
import aleksandra0KRBank.Exceptions.CancelledOperationException;
import aleksandra0KRBank.Exceptions.NotEnoughMoneyException;
import aleksandra0KRBank.Exceptions.NullAccountException;
import aleksandra0KRBank.Exceptions.UntrustedTransactionException;
import aleksandra0KRBank.Model.Account.Account;
import aleksandra0KRBank.Entity.Status.Status;

import java.math.BigDecimal;

public class CheckingForValidTransaction {
    public void CheckValidationOfTransaction(Account sender, BigDecimal money, Status status){
        if (status == Status.Cancelled) throw new CancelledOperationException();

        if (sender == null) throw new NullAccountException("Sender");

        if(!(sender instanceof BankAccount)){
            if(!sender.User.IsVerified()){
                if(money.compareTo(sender.Bank.UntrustedLimit) > 0) throw new UntrustedTransactionException(money);
            }

        }
    }
    public void CheckValidationOfTransaction(Account sender, Account receiver, BigDecimal money, Status status){
        if (status == Status.Cancelled) throw new CancelledOperationException();

        if (sender == null) throw new NullAccountException("Sender");
        if (receiver == null)  throw new NullAccountException("Receiver");

        if(sender.Money.compareTo(money) < 0) throw new NotEnoughMoneyException();
        if(!(sender instanceof BankAccount)){
            if(!sender.User.IsVerified()){
                if(money.compareTo(sender.Bank.UntrustedLimit) > 0) throw new UntrustedTransactionException(money);
            }

        }
        if(!(receiver instanceof BankAccount)){
            if(!receiver.User.IsVerified()){
                if(money.compareTo(receiver.Bank.UntrustedLimit) > 0) throw new UntrustedTransactionException(money);
            }

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
