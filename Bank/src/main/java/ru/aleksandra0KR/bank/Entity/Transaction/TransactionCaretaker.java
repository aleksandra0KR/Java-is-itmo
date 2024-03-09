package ru.aleksandra0KR.bank.Entity.Transaction;

import ru.aleksandra0KR.bank.Model.Transaction.Transaction;

import java.util.HashMap;
import java.util.UUID;

/**
 * The TransactionCaretaker class is responsible for managing the history of transactions and
 * providing functionality to back up and undo transactions.
 *
 * @author Aleksandra0KR
 * @version 1.0
 */
public class TransactionCaretaker {

  private final HashMap<UUID, Transaction> TransactionHistory = new HashMap<>();

  /**
   * Backs up a transaction by storing it in the transaction history along with a unique transaction
   * ID. Executes the transaction before storing it.
   *
   * @param transaction The transaction to be backed up.
   * @return The UUID of the backed up transaction.
   */
  public UUID Backup(Transaction transaction) {
    UUID transactionId = UUID.randomUUID();
    transaction.execute();
    this.TransactionHistory.put(transactionId, transaction);
    return transactionId;
  }

  /**
   * Undoes a transaction based on the provided transaction ID by retrieving the memento from the
   * history and canceling it.
   *
   * @param transactionId The UUID of the transaction to be undone.
   */
  public void Undo(UUID transactionId) {
    if (!TransactionHistory.containsKey(transactionId)) {
      return;
    }

    var memento = this.TransactionHistory.get(transactionId);
    memento.cancel();
    TransactionHistory.remove(memento);
  }

  public Transaction GetTransaction(UUID transactionId) {
    if (!TransactionHistory.containsKey(transactionId)) {
      return null;
    }
    return TransactionHistory.get(transactionId);
  }
}
