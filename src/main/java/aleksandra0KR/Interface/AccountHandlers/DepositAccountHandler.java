package aleksandra0KR.Interface.AccountHandlers;

import aleksandra0KR.Entity.Bank.Bank;
import aleksandra0KR.Entity.User.User;

import java.math.BigDecimal;

public class DepositAccountHandler extends AccountHandler {
    @Override
    public void HandleRequest(User user, Bank bank, String typeOfAccount, BigDecimal money) {
        if (typeOfAccount.equals("deposit"))
        {

            var account = bank.openDepositAccount(user, money);
            System.out.println("Deposit account is created! It's ID: " + account.getAccountId());

        }
        else
        {
           System.out.println("Bank doesn't have such type of account");
        }
    }
}
