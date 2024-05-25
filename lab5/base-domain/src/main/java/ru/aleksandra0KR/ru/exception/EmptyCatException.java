package ru.aleksandra0KR.ru.exception;

public class EmptyCatException extends CatExceptions {

  public EmptyCatException() {
    super("Cat's body is empty");
  }
}
