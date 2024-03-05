package aleksandra0KR.Exceptions;

public class DepositErrorTakeOffMoney extends TransactionException{
    public DepositErrorTakeOffMoney() {
        super("You can't take off money before close date!");
    }
}
