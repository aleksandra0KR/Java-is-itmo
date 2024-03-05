package aleksandra0KR.Entity.Transaction;

import aleksandra0KR.Model.Transaction.Transaction;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class TransactionCaretaker {
    private HashMap<UUID, Transaction> TransactionHistory = new HashMap<>();
    private Transaction _originator;

    public UUID Backup(Transaction transaction)
    {
        this._originator = transaction;
        UUID transactionId = UUID.randomUUID();
        transaction.execute();
        this.TransactionHistory.put(transactionId, this._originator);
        return transactionId;
    }

    public void Undo(UUID transactionId)
    {
        if (!TransactionHistory.containsKey(transactionId))
        {
            return;
        }

        var memento = this.TransactionHistory.get(transactionId);
        memento.cancel();
        this.TransactionHistory.remove(memento);
    }
}
