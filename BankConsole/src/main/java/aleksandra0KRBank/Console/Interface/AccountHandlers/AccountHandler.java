package aleksandra0KRBank.Console.Interface.AccountHandlers;

import aleksandra0KRBank.Entity.Bank.Bank;
import aleksandra0KRBank.Entity.User.User;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

public abstract class AccountHandler {
    @Setter
    @Getter
    public AccountHandler Successor;
    public abstract void HandleRequest(User user, Bank bank, String typeOfAccount, BigDecimal money, int years);
}
