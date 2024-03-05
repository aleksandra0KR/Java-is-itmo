package aleksandra0KRBank.Exceptions;

public class NullUserSurnameException extends UserExceptions{
    public NullUserSurnameException() {
        super("Surname can't be null");
    }
}
