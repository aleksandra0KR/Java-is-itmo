package aleksandra0KR.Interface.TransactionHandlers;

import aleksandra0KR.Interface.AccountHandlers.AccountHandler;
import aleksandra0KR.Interface.AccountHandlers.CreditAccountHandler;
import aleksandra0KR.Interface.AccountHandlers.DebitAccountHandler;
import aleksandra0KR.Interface.AccountHandlers.DepositAccountHandler;

public class TransactionHandlerBuilder {
    TransactionHandler TransactionHandler;
    public TransactionHandlerBuilder(){
        TransactionHandler = new TransferHandler();
        TransactionHandler.Successor = new ReplenishmentDepositHandler();
    }

    public TransactionHandler GetHandler(){
        return TransactionHandler;
    }
}
