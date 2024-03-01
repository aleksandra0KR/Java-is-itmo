package aleksandra0KR.Interface.TransactionHandlers;

import aleksandra0KR.Entity.Bank.Bank;
import aleksandra0KR.Entity.User.User;
import aleksandra0KR.Interface.AccountHandlers.AccountHandler;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

public abstract class TransactionHandler {
    @Setter
    @Getter
    public TransactionHandler Successor;
    public abstract void HandleRequest(String typeOfTransaction);
}
