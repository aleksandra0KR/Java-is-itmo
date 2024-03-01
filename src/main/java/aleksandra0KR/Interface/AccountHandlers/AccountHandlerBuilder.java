package aleksandra0KR.Interface.AccountHandlers;

public class AccountHandlerBuilder {

    AccountHandler AccountHandler;
    public AccountHandlerBuilder(){
        AccountHandler = new CreditAccountHandler();
        AccountHandler.Successor = new DebitAccountHandler();
        AccountHandler.Successor.Successor = new DepositAccountHandler();
    }

    public AccountHandler GetHandler(){
        return AccountHandler;
    }
}
