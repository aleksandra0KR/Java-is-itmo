package ru.aleksandra0KR.exceptions;

public class EmptyCatException extends CatExceptions {

  public EmptyCatException() {
    super("Cat's body is empty");
  }
}
