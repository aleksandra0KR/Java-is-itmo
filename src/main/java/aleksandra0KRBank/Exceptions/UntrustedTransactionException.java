package aleksandra0KRBank.Exceptions;

import java.math.BigDecimal;

public class UntrustedTransactionException extends TransactionException{
    public UntrustedTransactionException(BigDecimal money) {
        super(money.toString() + " is more then the banks limit for unverified accounts, operation can't be approved");
    }
}