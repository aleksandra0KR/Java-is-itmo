package ru.aleksandra0KR.exception;

public class OwnerPersonConnectionException extends PersonExceptions {

  public OwnerPersonConnectionException(String ownerName) {

    super("There is already person connected to: " + ownerName);
  }
}
