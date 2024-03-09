package ru.aleksandra0KR.bank.Exceptions;

/**
 * Exception class for the account
 *
 * @author Aleksandra0KR
 * @version 1.0
 */
public class NullAccountException extends TransactionException {

  public NullAccountException(String typeOfAccount) {

    super(typeOfAccount + " can't be null");
  }
}
