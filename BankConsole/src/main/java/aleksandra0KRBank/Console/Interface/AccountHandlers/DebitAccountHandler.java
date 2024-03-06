package aleksandra0KRBank.Console.Interface.AccountHandlers;

import aleksandra0KRBank.Entity.Bank.Bank;
import aleksandra0KRBank.Entity.User.User;

import java.math.BigDecimal;

/**
 * Class for handling creation of debit account requests
 * Extends AccountHandler class.
 * @author Aleksandra0KR
 * @version 1.0
 */
public class DebitAccountHandler extends AccountHandler {

    /**
     * Handles a request for a debit account creation.
     * If the request is for a debit account, it creates the account and prints its ID.
     * If not, it passes the request to the successor handler.
     * @param user The user associated with the account.
     * @param bank The bank associated with the account.
     * @param typeOfAccount The type of account requested.
     * @param money The initial amount of money.
     * @param years The number of years for the account.
     */
    @Override
    public void HandleRequest(User user, Bank bank, String typeOfAccount, BigDecimal money, int years) {
        if (typeOfAccount.equals("debit"))
        {

            var account = bank.openDebitAccount(user, money, years);
            System.out.println("Debit account is created! It's ID: " + account.getAccountId());

        }
        else if (Successor != null)
        {
            Successor.HandleRequest(user, bank, typeOfAccount, money, years);
        }

    }
}
