package aleksandra0KR.Entity.Transaction;

import aleksandra0KR.Model.Account.Account;
import aleksandra0KR.Entity.Status.Status;
import aleksandra0KR.Model.Transaction.Transaction;
import aleksandra0KR.Tools.CheckingForValidTransaction;
import lombok.AllArgsConstructor;

import java.math.BigDecimal;

@AllArgsConstructor
public class Withdraw implements Transaction {
    private Account Sender;
    private Account Receiver;
    private BigDecimal Money;
    private Status Status;

    @Override
    public void execute() {
        CheckingForValidTransaction checker = new CheckingForValidTransaction();
        checker.CheckValidationOfTransaction(Receiver, Sender, Money);

        Sender.Money.add(Money);
        Receiver.Money.subtract(Money);
        Status = Status.Valid;

    }

    @Override
    public void cancel() {

        CheckingForValidTransaction checker = new CheckingForValidTransaction();
        checker.CheckingForValidTransactionForCancel(Receiver, Sender, Money, Status);

        Sender.Money.subtract(Money);
        Receiver.Money.add(Money);

        Status = Status.Cancelled;
    }
}
