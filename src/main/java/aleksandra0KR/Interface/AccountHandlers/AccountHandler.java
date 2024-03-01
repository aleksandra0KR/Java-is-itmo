package aleksandra0KR.Interface.AccountHandlers;

import aleksandra0KR.Entity.Bank.Bank;
import aleksandra0KR.Entity.User.User;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

public abstract class AccountHandler {
    @Setter
    @Getter
    public AccountHandler Successor;
    public abstract void HandleRequest(User user, Bank bank, String typeOfAccount, BigDecimal money);
}
