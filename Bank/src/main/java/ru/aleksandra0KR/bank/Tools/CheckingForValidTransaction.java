package ru.aleksandra0KR.bank.Tools;

import ru.aleksandra0KR.bank.Entity.Account.BankAccount;
import ru.aleksandra0KR.bank.Exceptions.CancelledOperationException;
import ru.aleksandra0KR.bank.Exceptions.NotEnoughMoneyException;
import ru.aleksandra0KR.bank.Exceptions.NullAccountException;
import ru.aleksandra0KR.bank.Exceptions.UntrustedTransactionException;
import ru.aleksandra0KR.bank.Model.Account.Account;

import java.math.BigDecimal;

/**
 * Class for checking the validity of transactions
 * @author Aleksandra0KR
 * @version 1.0
 */

public class CheckingForValidTransaction {

    /**
     * Checks the validation of a transaction with a single sender.
     * @param sender The account sending the money.
     * @param money The amount of money being sent.
     * @param status The status of the transaction.
     * @throws CancelledOperationException customized exception if operation was cancelled
     * @throws NullAccountException customized exception if sender is null
     * @throws UntrustedTransactionException customized exception if exceeded the untrusted limit
    */
    public void CheckValidationOfTransaction(Account sender, BigDecimal money, Status status){
        if (status == Status.Cancelled) throw new CancelledOperationException();

        if (sender == null) throw new NullAccountException("Sender");

        if(!(sender instanceof BankAccount)){
            if(!sender.User.IsVerified()){
                if(money.compareTo(sender.Bank.UntrustedLimit) > 0) throw new UntrustedTransactionException(money);
            }

        }
    }

    /**
     * Checks the validation of a transaction between a sender and a receiver.
     * @param sender The account sending the money.
     * @param receiver The account receiving the money.
     * @param money The amount of money being sent.
     * @param status The status of the transaction.
     * @throws CancelledOperationException customized exception if operation was cancelled
     * @throws NullAccountException customized exception if sender is null
     * @throws UntrustedTransactionException customized exception if exceeded the untrusted limit
     * @throws NotEnoughMoneyException customized exception if not enough money on account
     */
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

    /**
     * Checks the validation of a cancellation transaction with a single sender.
     * @param sender The account initiating the cancellation.
     * @param money The amount of money involved in the cancellation.
     * @param status The status of the transaction being cancelled.
     * @throws CancelledOperationException customized exception if operation was cancelled
     * @throws NullAccountException customized exception if sender is null
     * @throws NotEnoughMoneyException customized exception if not enough money on account
     */
    public void CheckingForValidTransactionForCancel(Account sender, BigDecimal money, Status status){
        if (status == Status.Cancelled) throw new CancelledOperationException();

        if (sender == null) throw new NullAccountException("Sender");

        if(sender.Money.compareTo(money) < 0) throw new NotEnoughMoneyException();

    }

    /**
     * Checks the validation of a cancellation transaction between a sender and a receiver.
     * @param sender The account initiating the cancellation.
     * @param receiver The account involved in the cancellation.
     * @param money The amount of money involved in the cancellation.
     * @param status The status of the transaction being cancelled.
     * @throws CancelledOperationException customized exception if operation was cancelled
     * @throws NullAccountException customized exception if sender is null
     * @throws NotEnoughMoneyException customized exception if not enough money on account
     */
    public void CheckingForValidTransactionForCancel(Account sender, Account receiver, BigDecimal money, Status status){
        if (status == Status.Cancelled) throw new CancelledOperationException();

        if (sender == null) throw new NullAccountException("Sender");
        if (receiver == null) throw new NullAccountException("Receiver");

        if(receiver.Money.compareTo(money) < 0) throw new NotEnoughMoneyException();

    }

}
