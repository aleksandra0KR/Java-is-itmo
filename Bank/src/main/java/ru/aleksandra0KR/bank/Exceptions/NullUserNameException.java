package ru.aleksandra0KR.bank.Exceptions;

/**
 * Exception class for the name of a user
 * @author Aleksandra0KR
 * @version 1.0
 */
public class NullUserNameException extends UserExceptions{
    public NullUserNameException() {
        super("Name can't be null");
    }
}
