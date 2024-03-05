import aleksandra0KRBank.Entity.Account.BankAccount;
import aleksandra0KRBank.Entity.Account.CreditAccount;
import aleksandra0KRBank.Entity.Account.DebitAccount;
import aleksandra0KRBank.Entity.Account.DepositAccount;
import aleksandra0KRBank.Entity.Bank.Bank;
import aleksandra0KRBank.Entity.Bank.BankBuilder;
import aleksandra0KRBank.Entity.Bank.CentralBank;
import aleksandra0KRBank.Entity.Status.Status;
import aleksandra0KRBank.Entity.Transaction.Replenishment;
import aleksandra0KRBank.Entity.Transaction.Transfer;
import aleksandra0KRBank.Entity.User.User;
import aleksandra0KRBank.Entity.User.UserBuilder;
import aleksandra0KRBank.Model.Transaction.Transaction;
import aleksandra0KRBank.Tools.DepositMoneyGapPercentage;
import org.junit.jupiter.api.*;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BankTests {

    private DebitAccount debitAccount;
    private DepositAccount depositAccount;
    private CreditAccount creditAccount;
    private User user;
    private Bank bank;
    @BeforeEach
    public void SetUp() {
        CentralBank centralBank = CentralBank.getInstance();
        BankBuilder bankBuilder = new BankBuilder();
        bankBuilder.SetBankName("Tinkoff");

        var depositPercentage = new ArrayList<DepositMoneyGapPercentage>();

        BigDecimal lowerBoundary = new BigDecimal(0);
        BigDecimal topBoundary = new BigDecimal(100000);
        BigDecimal percentage = new BigDecimal(5);
        var percentageGap = new DepositMoneyGapPercentage(lowerBoundary, topBoundary, percentage);
        depositPercentage.add(percentageGap);

        lowerBoundary = new BigDecimal(100001);
        topBoundary = new BigDecimal(1000000000);
        percentage = new BigDecimal(10);
        percentageGap = new DepositMoneyGapPercentage(lowerBoundary, topBoundary, percentage);
        depositPercentage.add(percentageGap);

        bankBuilder.SetDepositPercentage(depositPercentage);

        BigDecimal creditLimit = new BigDecimal(1000000000);
        BigDecimal creditCommission = new BigDecimal(10);
        bankBuilder.SetCreditRules(creditLimit, creditCommission);

        BigDecimal debitPercentage = new BigDecimal(10);
        bankBuilder.SetDebitPercentage(debitPercentage);

        BigDecimal money = new BigDecimal(1000000000);
        bankBuilder.SetBankAccount(new BankAccount(money, centralBank.getBankCalendar()));

        BigDecimal untrustedLimit = new BigDecimal(1000000);
        bankBuilder.SetUntrustedRules(untrustedLimit);

        bank = bankBuilder.GetBank();
        centralBank.AddBank(bank);
        UserBuilder userBuilder = new UserBuilder();
        userBuilder.CreateUser("Vadim", "Milovachky");

        user =  userBuilder.GetUser();
        bank.AddUser(user);
        depositAccount = bank.openDepositAccount(user,  BigDecimal.valueOf(10000), 1);
        creditAccount = bank.openCreditAccount(user,  BigDecimal.valueOf(1000000), 10);
        debitAccount = bank.openDebitAccount(user,  BigDecimal.valueOf(50000), 5);

    }

    @Test
    public void OpenDepositAccountMakeReplenishment() {
        Transaction addMoney = new Replenishment(debitAccount, BigDecimal.valueOf(100000), Status.Created);
        addMoney.execute();
        Assertions.assertEquals(BigDecimal.valueOf(150000), debitAccount.Money);
    }

    @Test
    public void OpenDepositAccountGetPercentage() {
        CentralBank centralBank = CentralBank.getInstance();
        centralBank.AddTime(50);
        BigDecimal expected = new BigDecimal("16849.3151").setScale(10, RoundingMode.HALF_UP);
        BigDecimal actual = depositAccount.Money.setScale(10, RoundingMode.HALF_UP);
        assertEquals(expected, actual);
    }

    @Test
    public void MakeTransfer() {
        Transaction transaction = new Transfer(creditAccount, debitAccount, BigDecimal.valueOf(10000), Status.Created);
        transaction.execute();
        Assertions.assertEquals(BigDecimal.valueOf(990000), creditAccount.Money);
    }

    @Test
    public void Notifications() {
        bank.Attach(user);
        bank.Notify("Hello World!!!!!");

        Assertions.assertEquals(user.get_messages().get(0),"Hello World!!!!!");
    }

}
