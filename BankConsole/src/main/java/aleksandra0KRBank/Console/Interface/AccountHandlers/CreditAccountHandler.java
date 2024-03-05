package aleksandra0KRBank.Console.Interface.AccountHandlers;

import aleksandra0KRBank.Entity.Bank.Bank;
import aleksandra0KRBank.Entity.User.User;

import java.math.BigDecimal;

public class CreditAccountHandler extends AccountHandler {
    @Override
    public void HandleRequest(User user, Bank bank, String typeOfAccount, BigDecimal money, int years) {
        if (typeOfAccount.equals("credit"))
        {
            var account = bank.openCreditAccount(user, money, years);
            System.out.println("Credit account is created! It's ID: " + account.getAccountId());

        }
        else if (Successor != null)
        {
            Successor.HandleRequest(user, bank, typeOfAccount, money, years);
        }
    }
}
