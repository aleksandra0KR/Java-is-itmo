package aleksandra0KRBank.Console.Interface.TransactionHandlers;

import lombok.Getter;
import lombok.Setter;

/**
 * Abstract class representing a transaction handler.
 * Contains a method for handling transactions requests and a reference to the successor handler.
 *  @author Aleksandra0KR
 *  @version 1.0
 */
public abstract class TransactionHandler {
    @Setter
    @Getter
    public TransactionHandler Successor;
    public abstract void HandleRequest(String typeOfTransaction);
}
