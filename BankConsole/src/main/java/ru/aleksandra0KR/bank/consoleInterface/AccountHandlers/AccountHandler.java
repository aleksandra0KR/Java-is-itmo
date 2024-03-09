package ru.aleksandra0KR.bank.consoleInterface.AccountHandlers;

import ru.aleksandra0KR.bank.Entity.Bank.Bank;
import ru.aleksandra0KR.bank.Entity.User.User;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * Abstract class representing an account handler.
 * Contains a method for handling creation requests
 * and a reference to the successor handler.
 *
 * @author Aleksandra0KR
 * @version 1.0
 */
@Setter
@Getter
public abstract class AccountHandler {

  private AccountHandler Successor; // The successor handler for handling requests.

  /**
   * Handles a request for a specific type of account.
   *
   * @param user          The user associated with the account.
   * @param bank          The bank associated with the account.
   * @param typeOfAccount The type of account to handle the request for.
   * @param money         The initial amount of money.
   * @param years         The number of years.
   */
  public abstract void HandleRequest(User user, Bank bank, String typeOfAccount, BigDecimal money,
      int years);
}
