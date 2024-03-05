package aleksandra0KRBank.Interface.TransactionHandlers;

import aleksandra0KRBank.Entity.Bank.CentralBank;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.Scanner;
import java.util.UUID;

public class TransferHandler extends TransactionHandler {
    @Override
    public void HandleRequest(String typeOfTransaction) {
        if(Objects.equals(typeOfTransaction, "-transfer")){
            Scanner in = new Scanner(System.in);
            var centralBank = CentralBank.getInstance();

            System.out.println("Please, enter sender bank name:");
            String bankName = in.nextLine();

            var senderBank = centralBank.GetBank(bankName);
            if(senderBank == null){
                System.out.println("No such bank");
                return;
            }

            System.out.println("Please, enter sender's account ID:");
            UUID senderID = UUID.fromString(in.nextLine());
            var sender = senderBank.GetAccount(senderID);
            if(sender == null){
                System.out.println("No such account in a bank. You can register by running command -createAccount");
                return;
            }

            System.out.println("Please, enter receiver bank name:");
            bankName = in.nextLine();

            var receiverBank = centralBank.GetBank(bankName);
            if(receiverBank == null){
                System.out.println("No such bank");
                return;
            }

            System.out.println("Please, enter receiver's account ID:");
            UUID receiverID = UUID.fromString(in.nextLine());
            var receiver = receiverBank.GetAccount(receiverID);
            if(receiver == null){
                System.out.println("No such account in a bank. You can register by running command -createAccount");
                return;
            }

            System.out.println("Please, enter money:");
            BigDecimal money = new BigDecimal(in.nextLine());

            var resultID = centralBank.Transfer(sender, receiver, money);
            System.out.println("Transfer is completed, operation's ID: " + resultID);

        }
        else if(Successor != null)
        {
            Successor.HandleRequest(typeOfTransaction);
        }
    }
}
