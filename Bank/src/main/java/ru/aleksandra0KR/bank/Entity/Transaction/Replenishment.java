package ru.aleksandra0KR.bank.Entity.Transaction;

import ru.aleksandra0KR.bank.Tools.Status;
import ru.aleksandra0KR.bank.Model.Account.Account;
import ru.aleksandra0KR.bank.Model.Transaction.Transaction;
import ru.aleksandra0KR.bank.Tools.CheckingForValidTransaction;
import lombok.AllArgsConstructor;

import java.math.BigDecimal;

import static ru.aleksandra0KR.bank.Tools.Status.Cancelled;
import static ru.aleksandra0KR.bank.Tools.Status.Valid;


/**
 * Class for Replenishment Handles the process of adding money to an account.
 *
 * @author Aleksandra0KR
 * @version 1.0
 */
@AllArgsConstructor
public class Replenishment implements Transaction {

  private Account Account;
  private BigDecimal Money;
  private Status Status;

  /**
   * Executes the replenishment transaction. Validates the transaction, adds money to the account,
   * updates status, and logs the transaction.
   */
  @Override
  public void execute() {
    CheckingForValidTransaction checker = new CheckingForValidTransaction();
    checker.CheckValidationOfTransaction(Account, Money, Status);

    Account.FillUpMoney(Money);
    Status = Valid;
    Account.getHistoryOfTransactions().add(this);

  }

  /**
   * Cancels the replenishment transaction. Validates the cancellation, deducts money from the
   * account, and updates status to cancel.
   */
  @Override
  public void cancel() {
    CheckingForValidTransaction checker = new CheckingForValidTransaction();
    checker.CheckingForValidTransactionForCancel(Account, Money, Status);

    Account.setMoney(Account.getMoney().subtract(Money));

    Status = Cancelled;
  }

  /**
   * Prints information about the replenishment transaction.
   */
  @Override
  public void printInfo() {
    System.out.println(
        "Replenishment Account: " + Account.getAccountId() + " Money: " + Money + " Status: "
            + Status);
  }
}
