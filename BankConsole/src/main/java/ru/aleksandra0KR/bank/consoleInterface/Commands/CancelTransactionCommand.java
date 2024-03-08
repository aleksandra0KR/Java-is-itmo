package ru.aleksandra0KR.bank.consoleInterface.Commands;

import ru.aleksandra0KR.bank.Entity.Bank.Bank;
import ru.aleksandra0KR.bank.Entity.Bank.CentralBank;
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

        System.out.println("Please, enter bank name:");
        String SenderBankName = in.nextLine();
        Bank SenderBank = centralBank.GetBank(SenderBankName);

        System.out.println("Please, enter operation's ID:");
        UUID operationId = UUID.fromString(in.nextLine());

        if(centralBank.GetTransactionById(operationId) != null){
            centralBank.CancelTransaction(operationId);
            System.out.println("Operation is canceled");
            return;
        }

        SenderBank.CancelTransaction(operationId);
        System.out.println("Operation is canceled");

    }
}