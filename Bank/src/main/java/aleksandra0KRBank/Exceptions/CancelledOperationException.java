package aleksandra0KRBank.Exceptions;

/**
 * Exception class for the operation
 * @author Aleksandra0KR
 * @version 1.0
 */
public class CancelledOperationException extends TransactionException{
    public CancelledOperationException() {
        super("Cancelled operation can't be cancelled one more time");
    }
}
