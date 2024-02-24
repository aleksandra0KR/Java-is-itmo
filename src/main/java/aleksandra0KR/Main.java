package aleksandra0KR;

import aleksandra0KR.Entity.Account.BankAccount;
import aleksandra0KR.Entity.Account.CreditAccount;
import aleksandra0KR.Entity.Bank.Bank;
import aleksandra0KR.Entity.Bank.BankBuilder;
import aleksandra0KR.Entity.Bank.CentralBank;
import aleksandra0KR.Entity.User.User;
import aleksandra0KR.Entity.User.UserBuilder;
import aleksandra0KR.Model.Account.Account;
import aleksandra0KR.Tools.DepositMoneyGapPercentage;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class Main {
    public static void main(String[] args) {
        CentralBank centralBank = new CentralBank();

        BankBuilder bankBuilder = new BankBuilder();
        bankBuilder.SetBankName("BankName");
        Date closeDate = new Date(3000, 3, 14);
        bankBuilder.SetBankAccount(new BankAccount(BigDecimal.valueOf(10000), closeDate, bankBuilder.GetBank()));
        bankBuilder.SetDebitPercentage(BigDecimal.valueOf(10));
        List<DepositMoneyGapPercentage> depositPercentage = new ArrayList<>();
        depositPercentage.add(new DepositMoneyGapPercentage(BigDecimal.valueOf(0), BigDecimal.valueOf(10), BigDecimal.valueOf(2)));
        depositPercentage.add(new DepositMoneyGapPercentage(BigDecimal.valueOf(11), BigDecimal.valueOf(50), BigDecimal.valueOf(5)));
        depositPercentage.add(new DepositMoneyGapPercentage(BigDecimal.valueOf(51), BigDecimal.valueOf(1000), BigDecimal.valueOf(10)));
        bankBuilder.SetDepositPercentage(depositPercentage);
        bankBuilder.SetCreditLimit(BigDecimal.valueOf(10));
        bankBuilder.SetUntrustedLimit(BigDecimal.valueOf(10)); // TODO check lower -> error
        bankBuilder.SetCreditCommission(BigDecimal.valueOf(10));

        Bank bank = bankBuilder.GetBank();

        UserBuilder userBuilder = new UserBuilder();
        userBuilder.CreateUser("Name", "Surname");
        userBuilder.SetAddress("erfr");
        userBuilder.SetPassport("1234567890");

        User user1 = userBuilder.GetClient();


        userBuilder.CreateUser("Name2", "Surname2");
        userBuilder.SetAddress("erfr2");
        userBuilder.SetPassport("12345678902");

        User user2 = userBuilder.GetClient();

        bank.AddUser(user1);
        bank.AddUser(user2);

        Account account1 = new CreditAccount(BigDecimal.valueOf(1000), new Date(2026, 3, 14), user1, bank, bank.CreditLimit, bank.CreditCommission);
        Account account2 = new CreditAccount(BigDecimal.valueOf(9000), new Date(2026, 3, 14), user2, bank, bank.CreditLimit, bank.CreditCommission);
        bank.AddAccount(account1);
        bank.AddAccount(account2);

        centralBank.AddBank(bank);
        UUID idtrasfer = centralBank.Transfer(account1, account2, BigDecimal.valueOf(100));

        System.out.println(account1.Money);
        System.out.println(account2.Money);

        centralBank.CancelTransaction(idtrasfer);
        System.out.println(account1.Money);
        System.out.println(account2.Money);

    }
}