package aleksandra0KRBank.Console.Interface.TransactionHandlers;

public class TransactionHandlerBuilder {
    TransactionHandler TransactionHandler;
    public TransactionHandlerBuilder(){
        TransactionHandler = new TransferHandler();
        TransactionHandler.Successor = new WithdrawHandler();
        TransactionHandler.Successor.Successor = new ReplenishmentHandler();
    }

    public TransactionHandler GetHandler(){
        return TransactionHandler;
    }
}
