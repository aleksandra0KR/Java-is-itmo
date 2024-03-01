package aleksandra0KR.Exceptions;

public class NullUserSurnameException extends UserExceptions{
    public NullUserSurnameException() {
        super("Surname can't be null");
    }
}
