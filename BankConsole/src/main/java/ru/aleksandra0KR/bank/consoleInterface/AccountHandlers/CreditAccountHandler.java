package ru.aleksandra0KR.bank.consoleInterface.AccountHandlers;

import ru.aleksandra0KR.bank.Entity.Bank.Bank;
import ru.aleksandra0KR.bank.Entity.User.User;

import java.math.BigDecimal;

/**
 * Class for handling creation of credit account requests Extends AccountHandler class.
 *
 * @author Aleksandra0KR
 * @version 1.0
 */
public class CreditAccountHandler extends AccountHandler {

  /**
   * Handles a request for a credit account creation. If the request is for a credit account, it
   * creates the account and prints its ID. If not, it passes the request to the successor handler.
   *
   * @param user          The user associated with the account.
   * @param bank          The bank associated with the account.
   * @param typeOfAccount The type of account requested.
   * @param money         The initial amount of money.
   * @param years         The number of years for the account.
   */
  @Override
  public void HandleRequest(User user, Bank bank, String typeOfAccount, BigDecimal money,
      int years) {
    if (typeOfAccount.equals("credit")) {
      var account = bank.openCreditAccount(user, money, years);
      System.out.println("Credit account is created! It's ID: " + account.getAccountId());

    } else if (getSuccessor() != null) {
      getSuccessor().HandleRequest(user, bank, typeOfAccount, money, years);
    }
  }
}
