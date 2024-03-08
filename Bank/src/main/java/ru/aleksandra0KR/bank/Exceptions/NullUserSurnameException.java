package ru.aleksandra0KR.bank.Exceptions;

/**
 * Exception class for the surname of a user
 * @author Aleksandra0KR
 * @version 1.0
 */
public class NullUserSurnameException extends UserExceptions{
    public NullUserSurnameException() {

        super("Surname can't be null");
    }
}
