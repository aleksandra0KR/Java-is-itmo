package ru.aleksandra0KR.bank.Exceptions;

import java.math.BigDecimal;
/**
 * Exception class for the untrusted limit of money
 * @author Aleksandra0KR
 * @version 1.0
 */
public class UntrustedTransactionException extends TransactionException{
    public UntrustedTransactionException(BigDecimal money) {
        super(money.toString() + " is more then the banks limit for unverified accounts, operation can't be approved");
    }
}