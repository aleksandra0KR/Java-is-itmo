package ru.aleksandra0KR.bank.consoleInterface.TransactionHandlers;

import lombok.Getter;
import lombok.Setter;

/**
 * Abstract class representing a transaction handler. Contains a method for handling transaction
 * requests and a reference to the successor handler.
 *
 * @author Aleksandra0KR
 * @version 1.0
 */
@Setter
@Getter
public abstract class TransactionHandler {

  private TransactionHandler Successor;

  public abstract void HandleRequest(String typeOfTransaction);
}
