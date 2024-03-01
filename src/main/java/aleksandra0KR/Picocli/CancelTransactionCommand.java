package aleksandra0KR.Picocli;

import aleksandra0KR.Entity.Bank.CentralBank;
import picocli.CommandLine;

import java.util.Scanner;
import java.util.UUID;

@CommandLine.Command(name = "-cancelTransaction", description = "Cancels transaction")
public class CancelTransactionCommand implements Runnable{
    @Override
    public void run() {
        Scanner in = new Scanner(System.in);
        var centralBank = CentralBank.getInstance();

        System.out.println("Please, enter operation's ID:");
        UUID operationId = UUID.fromString(in.nextLine());
        centralBank.CancelTransaction(operationId);
        System.out.println("Operation is canceled");
    }
}
