package ru.aleksandra0KR.bank.consoleInterface.Commands;

import ru.aleksandra0KR.bank.consoleInterface.TransactionHandlers.TransactionHandler;
import ru.aleksandra0KR.bank.consoleInterface.TransactionHandlers.TransactionHandlerBuilder;
import picocli.CommandLine;

import java.util.Scanner;

/**
 * Command class for transaction performance
 * @author Aleksandra0KR
 * @version 1.0
 */
@CommandLine.Command(name = "-transaction", description = "Run transaction")
public class TransactionCommand implements Runnable{

    // Run transaction
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
