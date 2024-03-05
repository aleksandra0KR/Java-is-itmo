package aleksandra0KRBank.Entity.Transaction;

import aleksandra0KRBank.Entity.Status.Status;
import aleksandra0KRBank.Model.Account.Account;
import aleksandra0KRBank.Model.Transaction.Transaction;
import aleksandra0KRBank.Tools.CheckingForValidTransaction;
import lombok.AllArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

@AllArgsConstructor
public class Replenishment implements Transaction {
    private Account Account;
    private BigDecimal Money;
    private Status Status;

    @Override
    public void execute() {
        CheckingForValidTransaction checker = new CheckingForValidTransaction();
        checker.CheckValidationOfTransaction(Account, Money, Status);


        Account.FillUpMoney(Money);
        Status = Status.Valid;
        Account.HistoryOfTransactions.add(this);
    }

    @Override
    public void cancel() {

        CheckingForValidTransaction checker = new CheckingForValidTransaction();
        checker.CheckingForValidTransactionForCancel(Account, Money, Status);

        Account.Money = Account.Money.subtract(Money);

        Status = Status.Cancelled;
    }

    @Override
    public void printInfo() {
        System.out.println("Replenishment Account: " + Account.AccountId + " Money: " + Money + "Status: " + Status);
    }
}
