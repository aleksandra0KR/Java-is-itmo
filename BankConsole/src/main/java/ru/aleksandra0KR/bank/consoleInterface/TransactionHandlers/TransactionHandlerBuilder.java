package ru.aleksandra0KR.bank.consoleInterface.TransactionHandlers;

/**
 * Class for building a TransactionHandler
 *
 * @author Aleksandra0KR
 * @version 1.0
 */
public class TransactionHandlerBuilder {

  private final TransactionHandler TransactionHandler;  // The current transaction handler.

  /**
   * Constructs a new TransactionHandlerBuilder initialize new TransactionHandler and it's
   * Successors
   */
  public TransactionHandlerBuilder() {
    TransactionHandler = new TransferHandler();
    TransactionHandler.setSuccessor(new WithdrawHandler());
    TransactionHandler.getSuccessor().setSuccessor(new ReplenishmentHandler());
  }

  public TransactionHandler GetHandler() {
    return TransactionHandler;
  } // Return the current transaction handler.
}
