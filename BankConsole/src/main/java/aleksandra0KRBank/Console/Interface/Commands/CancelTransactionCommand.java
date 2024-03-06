package aleksandra0KRBank.Console.Interface.Commands;

import aleksandra0KRBank.Entity.Bank.CentralBank;
import picocli.CommandLine;

import java.util.Scanner;
import java.util.UUID;

/**
 * Command class for cancel operation by its ID
 * @author Aleksandra0KR
 * @version 1.0
 */
@CommandLine.Command(name = "-cancelTransaction", description = "Cancels transaction")
public class CancelTransactionCommand implements Runnable{

    // Cancels operation
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