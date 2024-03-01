package aleksandra0KR.Exceptions;

public class NullAccountException extends TransactionException{
    public NullAccountException(String typeOfAccount) {
        super(typeOfAccount + " can't be null");
    }
}
