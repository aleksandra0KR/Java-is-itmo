package aleksandra0KRBank.Exceptions;

public class CancelledOperationException extends TransactionException{
    public CancelledOperationException() {
        super("Cancelled operation can't be cancelled one more time");
    }
}
