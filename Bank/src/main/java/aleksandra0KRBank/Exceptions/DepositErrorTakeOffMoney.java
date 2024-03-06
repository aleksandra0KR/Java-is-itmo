package aleksandra0KRBank.Exceptions;

/**
 * Exception class for the deposit withdraw
 * @author Aleksandra0KR
 * @version 1.0
 */
public class DepositErrorTakeOffMoney extends TransactionException{
    public DepositErrorTakeOffMoney() {
        super("You can't take off money before close date!");
    }
}
