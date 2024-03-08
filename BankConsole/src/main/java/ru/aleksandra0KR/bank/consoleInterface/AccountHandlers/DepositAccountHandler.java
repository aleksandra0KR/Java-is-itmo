package ru.aleksandra0KR.bank.consoleInterface.AccountHandlers;

import ru.aleksandra0KR.bank.Entity.Bank.Bank;
import ru.aleksandra0KR.bank.Entity.User.User;

import java.math.BigDecimal;

/**
 * Class for handling creation of deposit account requests
 * Extends AccountHandler class.
 * @author Aleksandra0KR
 * @version 1.0
 */

public class DepositAccountHandler extends AccountHandler {

    /**
     * Handles a request for a deposit account creation.
     * If the request is for a deposit account, it creates the account and prints its ID.
     * If not, it prints error.
     * @param user The user associated with the account.
     * @param bank The bank associated with the account.
     * @param typeOfAccount The type of account requested.
     * @param money The initial amount of money.
     * @param years The number of years for the account.
     */
    @Override
    public void HandleRequest(User user, Bank bank, String typeOfAccount, BigDecimal money, int years) {
        if (typeOfAccount.equals("deposit"))
        {
            var account = bank.openDepositAccount(user, money, years);
            System.out.println("Deposit account is created! It's ID: " + account.getAccountId());

        }
        else
        {
           System.out.println("Bank doesn't have such type of account");
        }
    }
}
