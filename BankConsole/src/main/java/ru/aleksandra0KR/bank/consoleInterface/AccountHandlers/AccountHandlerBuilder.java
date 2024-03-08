package ru.aleksandra0KR.bank.consoleInterface.AccountHandlers;


/**
 * Class for building a AccountHandler
 * @author Aleksandra0KR
 * @version 1.0
 */
public class AccountHandlerBuilder {

    AccountHandler AccountHandler; // The current account handler.

    /**
     * Constructs a new AccountHandlerBuilder
     * initialize new AccountHandler and it's Successors
     */
    public AccountHandlerBuilder(){
        AccountHandler = new CreditAccountHandler();
        AccountHandler.Successor = new DebitAccountHandler();
        AccountHandler.Successor.Successor = new DepositAccountHandler();
    }

    public AccountHandler GetHandler(){
        return AccountHandler;
    } // Return the current account handler.
}
