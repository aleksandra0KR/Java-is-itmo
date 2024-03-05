package aleksandra0KRBank.Console.Interface.TransactionHandlers;

import aleksandra0KRBank.Entity.Bank.CentralBank;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.Scanner;
import java.util.UUID;

public class ReplenishmentHandler extends TransactionHandler {
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

            var resultID = centralBank.ReplenishmentDeposit(sender, money);
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
