package ru.aleksandra0KR.exceptions;

public class CatDoesntExistsException extends CatExceptions{

  public CatDoesntExistsException(String catName) {
    super("there is no cat with name" + catName);
  }
  public CatDoesntExistsException(Long catId) {
    super("there is no cat with id" + catId);
  }
}