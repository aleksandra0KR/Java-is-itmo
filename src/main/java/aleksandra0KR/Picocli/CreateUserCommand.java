package aleksandra0KR.Picocli;

import aleksandra0KR.Entity.Bank.CentralBank;
import aleksandra0KR.Entity.User.UserBuilder;
import picocli.CommandLine;

import java.util.Scanner;

@CommandLine.Command(name = "-createUser", description = "Creates new user of a bank")
public class CreateUserCommand implements Runnable{
    @Override
    public void run() {
        Scanner in = new Scanner(System.in);
        var centralBank = CentralBank.getInstance();

        System.out.println("Please, enter bank name:");
        String bankName = in.nextLine();

        var bank = centralBank.GetBank(bankName);
        if(bank == null) {
            System.out.println("No such bank");
            return;
        }

        System.out.println("Please, enter user's name:");
        String name = in.nextLine();

        System.out.println("Please, enter user's surname:");
        String surname = in.nextLine();

        var userBuilder = new UserBuilder();
        userBuilder.CreateUser(name, surname);

        System.out.println("Please, enter user's passport:");
        String passport = in.nextLine();
        userBuilder.SetPassport(passport);

        System.out.println("Please, enter user's address:");
        String address = in.nextLine();
        userBuilder.SetAddress(address);

        var user = userBuilder.GetUser();
        bank.AddUser(user);

        System.out.println("User is created! ID: " + user.getID());

        }
}
