package aleksandra0KR.Interface.AccountHandlers;

import aleksandra0KR.Entity.Bank.Bank;
import aleksandra0KR.Entity.User.User;

import java.math.BigDecimal;

public class CreditAccountHandler extends AccountHandler {
    @Override
    public void HandleRequest(User user, Bank bank, String typeOfAccount, BigDecimal money) {
        if (typeOfAccount.equals("credit"))
        {

            var account = bank.openCreditAccount(user, money);
            System.out.println("Credit account is created! It's ID: " + account.getAccountId());

        }
        else if (Successor != null)
        {
            Successor.HandleRequest(user, bank, typeOfAccount, money);
        }
    }
}
