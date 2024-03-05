package aleksandra0KR.Picocli;

import aleksandra0KR.Entity.Bank.CentralBank;
import aleksandra0KR.Interface.AccountHandlers.AccountHandlerBuilder;
import aleksandra0KR.Interface.TransactionHandlers.TransactionHandler;
import aleksandra0KR.Interface.TransactionHandlers.TransactionHandlerBuilder;
import picocli.CommandLine;

import java.util.Scanner;

@CommandLine.Command(name = "-transaction", description = "Run transaction")
public class TransactionCommand implements Runnable{
    @Override
    public void run() {
        Scanner in = new Scanner(System.in);

        System.out.println("Please, enter type of transaction: ");
        System.out.println("-replenishment");
        System.out.println("-transfer");
        System.out.println("-withdraw");
        System.out.println("Please, enter type of transaction: ");
        String typeOfTransaction = in.nextLine();

        TransactionHandlerBuilder handlerBuilder = new TransactionHandlerBuilder();
        TransactionHandler handler = handlerBuilder.GetHandler();
        handler.HandleRequest(typeOfTransaction);
    }
}
