package aleksandra0KR.Exceptions;

import java.math.BigDecimal;

public class NotEnoughMoneyException extends TransactionException{
    public NotEnoughMoneyException() {
        super("On your account not enough money");
    }
}
