package aleksandra0KRBank.Interface.AccountHandlers;

import aleksandra0KRBank.Entity.Bank.Bank;
import aleksandra0KRBank.Entity.User.User;

import java.math.BigDecimal;

public class DepositAccountHandler extends AccountHandler {
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
