package aleksandra0KRBank.Interface.AccountHandlers;

import aleksandra0KRBank.Entity.Bank.Bank;
import aleksandra0KRBank.Entity.User.User;

import java.math.BigDecimal;

public class DebitAccountHandler extends AccountHandler {

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
