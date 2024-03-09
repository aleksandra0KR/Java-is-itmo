package ru.aleksandra0KR.bank.Tools;

import ru.aleksandra0KR.bank.Entity.Account.BankAccount;
import ru.aleksandra0KR.bank.Entity.Account.CreditAccount;
import ru.aleksandra0KR.bank.Exceptions.CancelledOperationException;
import ru.aleksandra0KR.bank.Exceptions.NotEnoughMoneyException;
import ru.aleksandra0KR.bank.Exceptions.NullAccountException;
import ru.aleksandra0KR.bank.Exceptions.UntrustedTransactionException;
import ru.aleksandra0KR.bank.Model.Account.Account;

import java.math.BigDecimal;

/**
 * Class for checking the validity of transactions
 *
 * @author Aleksandra0KR
 * @version 1.0
 */

public class CheckingForValidTransaction {

  /**
   * Checks the validation of a transaction with a single sender.
   *
   * @param account The account sending the money.
   * @param status  The status of the transaction.
   * @throws CancelledOperationException   customized exception if operation was canceled
   * @throws NullAccountException          customized exception if sender is null
   * @throws UntrustedTransactionException customized exception if exceeded the untrusted limit
   */
  public void CheckValidationOfTransaction(Account account, BigDecimal money, Status status) {
      if (status == Status.Cancelled) {
          throw new CancelledOperationException();
      }

      if (account == null) {
          throw new NullAccountException("Account");
      }

    if (!(account instanceof BankAccount)) {
      if (!account.getUser().IsVerified()) {
          if (money.compareTo(account.getBank().getUntrustedLimit()) > 0) {
              throw new UntrustedTransactionException(money);
          }
      }
    }
  }

  /**
   * Checks the validation of the transaction amount against the sender's account balance. If the
   * sender's account balance is less than the transaction amount: If the sender is a CreditAccount,
   * checks if the remaining balance after the transaction exceeds the credit limit.
   *
   * @param sender The account initiating the transaction.
   * @param money  The amount of money involved in the transaction.
   * @throws NotEnoughMoneyException customized exception
   */
  public void CheckValidationOfTransactionMoney(Account sender, BigDecimal money) {
    if (sender.getMoney().compareTo(money) < 0) {
      if ((sender instanceof CreditAccount)) {
        BigDecimal finalMoney = sender.getMoney().subtract(money);
          if (sender.getBank().getCreditLimit().compareTo(finalMoney) < 0) {
              throw new NotEnoughMoneyException();
          }
      }
      throw new NotEnoughMoneyException();
    }
  }

  /**
   * Checks the validation of a cancellation transaction with a single sender.
   *
   * @param sender The account initiating the cancellation.
   * @param money  The amount of money involved in the cancellation.
   * @param status The status of the transaction being canceled.
   * @throws CancelledOperationException customized exception if operation was canceled
   * @throws NullAccountException        customized exception if sender is null
   * @throws NotEnoughMoneyException     customized exception if not enough money on account
   */
  public void CheckingForValidTransactionForCancel(Account sender, BigDecimal money,
      Status status) {
      if (status == Status.Cancelled) {
          throw new CancelledOperationException();
      }

      if (sender == null) {
          throw new NullAccountException("Sender");
      }

      if (sender.getMoney().compareTo(money) < 0) {
          throw new NotEnoughMoneyException();
      }

  }

  /**
   * Checks the validation of a cancellation transaction between a sender and a receiver.
   *
   * @param sender   The account initiating the cancellation.
   * @param receiver The account involved in the cancellation.
   * @param money    The amount of money involved in the cancellation.
   * @param status   The status of the transaction being canceled.
   * @throws CancelledOperationException customized exception if operation was canceled
   * @throws NullAccountException        customized exception if sender is null
   * @throws NotEnoughMoneyException     customized exception if not enough money on account
   */
  public void CheckingForValidTransactionForCancel(Account sender, Account receiver,
      BigDecimal money, Status status) {
      if (status == Status.Cancelled) {
          throw new CancelledOperationException();
      }

      if (sender == null) {
          throw new NullAccountException("Sender");
      }
      if (receiver == null) {
          throw new NullAccountException("Receiver");
      }

    if (receiver.getMoney().compareTo(money) < 0)
      throw new NotEnoughMoneyException();

  }

}
