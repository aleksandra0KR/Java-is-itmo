package aleksandra0KRBank.Interface.Commands;

import aleksandra0KRBank.Entity.Bank.CentralBank;
import picocli.CommandLine;

import java.util.Scanner;
import java.util.UUID;

@CommandLine.Command(name = "-cancelTransaction", description = "Cancels transaction")
public class CancelTransactionCommand implements Runnable{
    @Override
    public void run() {
        Scanner in = new Scanner(System.in);
        CentralBank centralBank = CentralBank.getInstance();

        System.out.println("Please, enter operation's ID:");
        UUID operationId = UUID.fromString(in.nextLine());
        centralBank.CancelTransaction(operationId);
        System.out.println("Operation is canceled");
    }
}