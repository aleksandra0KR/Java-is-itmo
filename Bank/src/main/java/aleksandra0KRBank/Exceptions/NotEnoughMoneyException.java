package aleksandra0KRBank.Exceptions;

public class NotEnoughMoneyException extends TransactionException{
    public NotEnoughMoneyException() {
        super("On your account not enough money");
    }
}
