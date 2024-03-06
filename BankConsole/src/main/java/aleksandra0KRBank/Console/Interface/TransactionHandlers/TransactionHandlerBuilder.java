package aleksandra0KRBank.Console.Interface.TransactionHandlers;

/**
 * Class for building a TransactionHandler
 * @author Aleksandra0KR
 * @version 1.0
 */
public class TransactionHandlerBuilder {
    TransactionHandler TransactionHandler;  // The current transaction handler.

    /**
     * Constructs a new TransactionHandlerBuilder
     * initialize new TransactionHandler and it's Successors
     */
    public TransactionHandlerBuilder(){
        TransactionHandler = new TransferHandler();
        TransactionHandler.Successor = new WithdrawHandler();
        TransactionHandler.Successor.Successor = new ReplenishmentHandler();
    }

    public TransactionHandler GetHandler(){
        return TransactionHandler;
    } // Return the current transaction handler.
}
