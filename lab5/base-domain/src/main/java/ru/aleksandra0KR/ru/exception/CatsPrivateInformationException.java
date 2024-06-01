package ru.aleksandra0KR.ru.exception;

public class CatsPrivateInformationException extends CatExceptions {

  public CatsPrivateInformationException() {
    super("Only owners can get access to private information");
  }

}
