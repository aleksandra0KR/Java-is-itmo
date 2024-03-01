package aleksandra0KR.Exceptions;

public class TransactionException extends RuntimeException{
    public TransactionException(String errorMessage) {
        super(errorMessage);
    }
}
