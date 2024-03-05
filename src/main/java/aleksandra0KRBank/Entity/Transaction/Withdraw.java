package aleksandra0KRBank.Entity.Transaction;

import aleksandra0KRBank.Model.Account.Account;
import aleksandra0KRBank.Entity.Status.Status;
import aleksandra0KRBank.Model.Transaction.Transaction;
import aleksandra0KRBank.Tools.CheckingForValidTransaction;
import lombok.AllArgsConstructor;

import java.math.BigDecimal;

@AllArgsConstructor
public class Withdraw implements Transaction {
    private Account Account;
    private BigDecimal Money;
    private Status Status;

    @Override
    public void execute() {
        CheckingForValidTransaction checker = new CheckingForValidTransaction();
        checker.CheckValidationOfTransaction(Account, Money, Status);
        Account.TakeOffMoney(Money);
        Status = Status.Valid;
        Account.HistoryOfTransactions.add(this);

    }

    @Override
    public void cancel() {

        CheckingForValidTransaction checker = new CheckingForValidTransaction();
        checker.CheckingForValidTransactionForCancel(Account, Money, Status);

        Account.Money = Account.Money.add(Money);

        Status = Status.Cancelled;
    }

    @Override
    public void printInfo() {
        System.out.println("Withdraw from Account: " + Account.AccountId + " Money: " + Money + "Status: " + Status);
    }
}
