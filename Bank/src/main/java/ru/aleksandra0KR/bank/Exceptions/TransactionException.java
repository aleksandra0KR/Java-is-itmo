package ru.aleksandra0KR.bank.Exceptions;

/**
 * Exception class for the transaction
 *
 * @author Aleksandra0KR
 * @version 1.0
 */
public class TransactionException extends RuntimeException {

  public TransactionException(String errorMessage) {

    super(errorMessage);
  }
}
