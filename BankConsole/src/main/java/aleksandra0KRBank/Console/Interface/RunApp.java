package aleksandra0KRBank.Console.Interface;


import aleksandra0KRBank.Console.Interface.Commands.*;
import picocli.CommandLine;

import java.util.*;

/**
 * Main class to run the banking application with various commands.
 * @author Aleksandra0KR
 * @version 1.0
 */

@CommandLine.Command(name = "myapp", subcommands = {StopCommand.class, HelpCommand.class,
        CreateUserCommand.class, CreateBankCommand.class, CreateAccountCommand.class,
        AddAddressCommand.class, AddPassportCommand.class, TransactionCommand.class,
        CancelTransactionCommand.class, ShowBalanceCommand.class, AddDaysCommand.class,
        ShowHistoryOfTransactionsCommand.class, NotifyUsersCommand.class, ShowNotificationsCommand.class,
        SubscribeAccountOnNotificationsCommand.class, UnSubscribeAccountFromNotificationsCommand.class,
        ChangeCreditCommissionCommand.class, ChangeCreditLimitCommand.class, ChangeDebitPercentageCommand.class,
        ChangeDepositPercentageCommand.class, ChangeUntrustedLimitCommand.class})
public class RunApp {
    private static final Map<String, Runnable> COMMANDS = new HashMap<>();

    static {
        COMMANDS.put("-stop", new StopCommand());
        COMMANDS.put("-help", new HelpCommand());
        COMMANDS.put("-showHistoryOfTransactions", new ShowHistoryOfTransactionsCommand());
        COMMANDS.put("-createUser", new CreateUserCommand());
        COMMANDS.put("-createBank", new CreateBankCommand());
        COMMANDS.put("-createAccount", new CreateAccountCommand());
        COMMANDS.put("-addPassport", new AddPassportCommand());
        COMMANDS.put("-addAddress", new AddAddressCommand());
        COMMANDS.put("-transaction", new TransactionCommand());
        COMMANDS.put("-cancelTransaction", new CancelTransactionCommand());
        COMMANDS.put("-showBalance", new ShowBalanceCommand());
        COMMANDS.put("-addDays", new AddDaysCommand());
        COMMANDS.put("-notifyUsers", new NotifyUsersCommand());
        COMMANDS.put("-showNotifications", new ShowNotificationsCommand());
        COMMANDS.put("-subscribeAccountOnNotifications", new SubscribeAccountOnNotificationsCommand());
        COMMANDS.put("-unSubscribeAccountFROMNotifications", new UnSubscribeAccountFromNotificationsCommand());
        COMMANDS.put("-changeCreditCommission", new ChangeCreditCommissionCommand());
        COMMANDS.put("-changeCreditLimit", new ChangeCreditLimitCommand());
        COMMANDS.put("-changeDebitPercentage", new ChangeDebitPercentageCommand());
        COMMANDS.put("-changeDepositPercentage", new ChangeDepositPercentageCommand());
        COMMANDS.put("-changeUntrustedLimit", new ChangeUntrustedLimitCommand());

    }

    /**
     * Main method to run the bank application and handle user commands.
     * @param args Command line arguments.
     */

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        CommandLine cmd = new CommandLine(new RunApp());

        while (true) {
            System.out.println("Enter a command:");
            String input = scanner.nextLine();

            COMMANDS.getOrDefault(input, () -> System.out.println("Invalid command. Please try again. Or Enter -help for list of commands")).run();
        }
    }
}

