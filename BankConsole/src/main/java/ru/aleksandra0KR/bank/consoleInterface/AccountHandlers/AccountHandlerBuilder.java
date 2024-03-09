package ru.aleksandra0KR.bank.consoleInterface.AccountHandlers;


/**
 * Class for building an AccountHandler
 *
 * @author Aleksandra0KR
 * @version 1.0
 */
public class AccountHandlerBuilder {

  private final AccountHandler AccountHandler; // The current account handler.

  /**
   * Constructs a new AccountHandlerBuilder initialize new AccountHandler and it's Successors
   */
  public AccountHandlerBuilder() {
    AccountHandler = new CreditAccountHandler();
    AccountHandler.setSuccessor(new DebitAccountHandler());
    AccountHandler.getSuccessor().setSuccessor(new DepositAccountHandler());
  }

  public AccountHandler GetHandler() {
    return AccountHandler;
  } // Return the current account handler.
}
