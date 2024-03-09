package ru.aleksandra0KR.bank.Entity.Bank;

import lombok.Setter;
import ru.aleksandra0KR.bank.Model.Transaction.Transaction;
import ru.aleksandra0KR.bank.Tools.Status;
import ru.aleksandra0KR.bank.Entity.Transaction.TransactionCaretaker;
import ru.aleksandra0KR.bank.Entity.Transaction.Transfer;
import ru.aleksandra0KR.bank.Model.Account.Account;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Month;
import java.util.*;

/**
 * Class for the central bank
 *
 * @author Aleksandra0KR
 * @version 1.0
 */
public class CentralBank {

  private static CentralBank instance;

  /**
   * List of all banks
   */
  private final List<Bank> _banks;

  @Getter
  @Setter
  private LocalDate BankCalendar;
  private final TransactionCaretaker TransactionCaretaker;

  /**
   * Private constructor to initialize the CentralBank instance with default values.
   */
  private CentralBank() {
    BankCalendar = LocalDate.now();
    _banks = new ArrayList<>();
    TransactionCaretaker = new TransactionCaretaker();
  }

  /**
   * Get the singleton instance of CentralBank.
   *
   * @return The singleton instance of CentralBank.
   */
  public static CentralBank getInstance() {
      if (instance == null) {
          instance = new CentralBank();
      }
    return instance;
  }

  /**
   * Get a specific bank by its name.
   *
   * @param bankName The name of the bank to return.
   * @return The Bank object, or null if not found.
   */

  public Bank GetBank(String bankName) {

    for (Bank bank : _banks) {
        if (bank.getName().compareTo(bankName) == 0) {
            return bank;
        }
    }
    return null;
  }

  /**
   * Adds a new bank to the central bank's list of banks.
   *
   * @param bank The Bank object to add.
   */
  public void AddBank(Bank bank) {
    if (_banks.stream().noneMatch(a -> a.getBankId().compareTo(bank.getBankId()) == 0)) {
      bank.setBankCalendar(BankCalendar);
      _banks.add(bank);
    }
  }

  /**
   * Checks for a new month and notify all banks if a new month has started.
   *
   * @param oldMonth The previous month value.
   * @param oldYear  The previous year value.
   */
  public void CheckForNewMoth(Month oldMonth, int oldYear) {

    Month newMonth = BankCalendar.getMonth();
    int newYear = BankCalendar.getYear();
    if (newMonth.compareTo(oldMonth) != 0 || newYear != oldYear) {
      for (Bank bank : _banks) {
        bank.NotifyNewMonth();
      }
    }
  }

  /**
   * Cancel a specific transaction by its UUID identifier.
   *
   * @param transactionId The UUID of the transaction to cancel.
   */
  public void CancelTransaction(UUID transactionId) {
    TransactionCaretaker.Undo(transactionId);
  }

  /**
   * Initiate a transfer transaction between two accounts and back up the transaction.
   *
   * @param sender   The sender Account object.
   * @param receiver The receiver Account object.
   * @param amount   The amount of money to transfer.
   * @return The UUID of the initiated transfer transaction.
   */
  public UUID Transfer(Account sender, Account receiver, BigDecimal amount) {
    return TransactionCaretaker.Backup(new Transfer(sender, receiver, amount, Status.Created));
  }

  /**
   * Add a specified number of days to the central bank's calendar and notify banks about the time
   * change.
   *
   * @param days The number of days to add to the current date in the calendar.
   */
  public void AddTime(int days) {
    Month oldMonth = BankCalendar.getMonth();
    int oldYear = BankCalendar.getYear();
    BankCalendar = BankCalendar.plusDays(days);
    for (Bank bank : _banks) {
      bank.NotifyNewTime(days);
    }
    this.CheckForNewMoth(oldMonth, oldYear);
  }

  /**
   * Returns a transaction by its unique ID using the TransactionCaretaker.
   *
   * @param transactionId The unique ID of the transaction to return.
   * @return The transaction corresponding to the provided transactionId.
   */
  public Transaction GetTransactionById(UUID transactionId) {
    return TransactionCaretaker.GetTransaction(transactionId);
  }

}
