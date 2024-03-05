package aleksandra0KRBank.Console.Interface.TransactionHandlers;

import lombok.Getter;
import lombok.Setter;

public abstract class TransactionHandler {
    @Setter
    @Getter
    public TransactionHandler Successor;
    public abstract void HandleRequest(String typeOfTransaction);
}
