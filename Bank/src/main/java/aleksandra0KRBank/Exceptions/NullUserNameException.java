package aleksandra0KRBank.Exceptions;

public class NullUserNameException extends UserExceptions{
    public NullUserNameException() {
        super("Name can't be null");
    }
}
