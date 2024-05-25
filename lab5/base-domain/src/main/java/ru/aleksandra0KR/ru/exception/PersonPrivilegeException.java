package ru.aleksandra0KR.ru.exception;

public class PersonPrivilegeException extends PersonExceptions {

  public PersonPrivilegeException() {
    super("Only Admins can create owners");
  }
}
