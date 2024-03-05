package aleksandra0KRBank.Exceptions;

public class TransactionException extends RuntimeException{
    public TransactionException(String errorMessage) {
        super(errorMessage);
    }
}
