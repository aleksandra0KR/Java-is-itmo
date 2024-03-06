package aleksandra0KRBank.Console.Interface.Commands;

import aleksandra0KRBank.Entity.Bank.Bank;
import aleksandra0KRBank.Entity.Bank.CentralBank;
import aleksandra0KRBank.Entity.User.User;
import aleksandra0KRBank.Entity.User.UserBuilder;
import picocli.CommandLine;

import java.util.Scanner;

/**
 * Command class for creating a user in a bank
 * @author Aleksandra0KR
 * @version 1.0
 */
@CommandLine.Command(name = "-createUser", description = "Creates new user of a bank")
public class CreateUserCommand implements Runnable{

    // Creates new user of a bank
    @Override
    public void run() {
        Scanner in = new Scanner(System.in);
        CentralBank centralBank = CentralBank.getInstance();

        System.out.println("Please, enter bank name:");
        String bankName = in.nextLine();

        Bank bank = centralBank.GetBank(bankName);
        if(bank == null) {
            System.out.println("No such bank");
            return;
        }

        System.out.println("Please, enter user's name:");
        String name = in.nextLine();

        System.out.println("Please, enter user's surname:");
        String surname = in.nextLine();

        UserBuilder userBuilder = new UserBuilder();
        userBuilder.CreateUser(name, surname);

        System.out.println("Please, enter user's passport:");
        String passport = in.nextLine();
        userBuilder.SetPassport(passport);

        System.out.println("Please, enter user's address:");
        String address = in.nextLine();
        userBuilder.SetAddress(address);

        User user = userBuilder.GetUser();
        bank.AddUser(user);

        System.out.println("User is created! ID: " + user.getID());

        }
}
