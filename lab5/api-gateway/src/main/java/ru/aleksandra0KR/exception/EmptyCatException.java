package ru.aleksandra0KR.exception;

public class EmptyCatException extends CatExceptions {

  public EmptyCatException() {
    super("Cat's body is empty");
  }
}
