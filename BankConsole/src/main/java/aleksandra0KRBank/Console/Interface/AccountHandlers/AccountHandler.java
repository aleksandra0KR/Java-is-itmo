package aleksandra0KRBank.Console.Interface.AccountHandlers;

import aleksandra0KRBank.Entity.Bank.Bank;
import aleksandra0KRBank.Entity.User.User;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * Abstract class representing an account handler.
 * Contains a method for handling creation requests and a reference to the successor handler.
 *  @author Aleksandra0KR
 *  @version 1.0
 */
public abstract class AccountHandler {
    @Setter
    @Getter
    public AccountHandler Successor; // The successor handler for handling requests.

    /**
     Handles a request for a specific type of account.
     @param user The user associated with the account.
     @param bank The bank associated with the account.
     @param typeOfAccount The type of account to handle the request for.
     @param money The initial amount of money.
     @param years The number of years.
     */
    public abstract void HandleRequest(User user, Bank bank, String typeOfAccount, BigDecimal money, int years);
}
