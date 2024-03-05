package aleksandra0KR.Entity.Transaction;

import aleksandra0KR.Model.Transaction.Transaction;
import aleksandra0KR.Model.Account.Account;
import aleksandra0KR.Entity.Status.Status;
import aleksandra0KR.Tools.CheckingForValidTransaction;
import lombok.AllArgsConstructor;


import java.math.BigDecimal;
@AllArgsConstructor
public class Transfer implements Transaction {
    private Account Sender;
    private Account Receiver;
    private BigDecimal Money;
    private Status Status;

    @Override
    public void execute() {
        CheckingForValidTransaction checker = new CheckingForValidTransaction();
        checker.CheckValidationOfTransaction(Sender, Receiver, Money, Status);

        Sender.Money = Sender.Money.subtract(Money);
        Receiver.Money =  Receiver.Money.add(Money);
        Status = Status.Valid;
        Sender.HistoryOfTransactions.add(this);
        Receiver.HistoryOfTransactions.add(this);

    }

    @Override
    public void cancel() {

        CheckingForValidTransaction checker = new CheckingForValidTransaction();
        checker.CheckingForValidTransactionForCancel(Sender, Receiver, Money,Status);

        Receiver.Money = Receiver.Money.subtract(Money);
        Sender.Money = Sender.Money.add(Money);

        Status = Status.Cancelled;
    }

    @Override
    public void printInfo() {
        System.out.println("Transaction Sender: " + Receiver.AccountId + " Receiver: " + Sender.AccountId + " Money: " + Money);
    }
}
