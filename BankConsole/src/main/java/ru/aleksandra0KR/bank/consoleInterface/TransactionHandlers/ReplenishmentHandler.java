package ru.aleksandra0KR.bank.consoleInterface.TransactionHandlers;

import ru.aleksandra0KR.bank.Entity.Bank.CentralBank;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.Scanner;
import java.util.UUID;

/**
 * Class for handling performance of replenishment transaction requests
 * Extends TransactionHandler class.
 * @author Aleksandra0KR
 * @version 1.0
 */
public class ReplenishmentHandler extends TransactionHandler {

    /**
    * Handles a request for a replenishment transaction.
    * If the request is for a replenishment transaction, it prompts for bank and account details, processes the replenishment, and prints the operation's ID.
    * If not, it provides a list of available operations.
    * @param typeOfTransaction The type of transaction requested.
*/
    @Override
    public void HandleRequest(String typeOfTransaction) {
        if(Objects.equals(typeOfTransaction, "-replenishment")){
            Scanner in = new Scanner(System.in);
            var centralBank = CentralBank.getInstance();

            System.out.println("Please, enter bank name:");
            String bankName = in.nextLine();

            var senderBank = centralBank.GetBank(bankName);
            if(senderBank == null){
                System.out.println("No such bank");
                return;
            }

            System.out.println("Please, enter account ID:");
            UUID senderID = UUID.fromString(in.nextLine());
            var sender = senderBank.GetAccount(senderID);
            if(sender == null){
                System.out.println("No such account in a bank. You can register by running command -createAccount");
                return;
            }

            System.out.println("Please, enter money:");
            BigDecimal money = new BigDecimal(in.nextLine());

            var resultID = centralBank.Replenishment(sender, money);
            System.out.println("Replenishment is completed, operation's ID: " + resultID);

        }
        else
        {
            System.out.println("There is no such operation");
            System.out.println("List of operations: ");
            System.out.println("-replenishment");
            System.out.println("-transfer");
            System.out.println("-withdraw");
        }
    }

}
