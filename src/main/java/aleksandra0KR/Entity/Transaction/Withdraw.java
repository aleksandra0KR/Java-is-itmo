package aleksandra0KR.Entity.Transaction;

import aleksandra0KR.Model.Account.Account;
import aleksandra0KR.Entity.Status.Status;
import aleksandra0KR.Model.Transaction.Transaction;
import aleksandra0KR.Tools.CheckingForValidTransaction;
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
        System.out.println("Withdraw from Account: " + Account.AccountId + " Money: " + Money);
    }
}
