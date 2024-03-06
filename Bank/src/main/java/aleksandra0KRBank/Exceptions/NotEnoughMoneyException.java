package aleksandra0KRBank.Exceptions;

/**
 * Exception class for the operation
 * @author Aleksandra0KR
 * @version 1.0
 */
public class NotEnoughMoneyException extends TransactionException{
    public NotEnoughMoneyException() {
        super("On your account not enough money");
    }
}
