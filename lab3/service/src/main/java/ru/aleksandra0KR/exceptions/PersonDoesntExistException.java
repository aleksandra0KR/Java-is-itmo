package ru.aleksandra0KR.exceptions;

public class PersonDoesntExistException extends PersonExceptions{
  public PersonDoesntExistException(Long  personId) {

    super("Person not found with ID: " + personId);
  }

  public PersonDoesntExistException(String  name) {

    super("Person not found with name: " + name);
  }
}