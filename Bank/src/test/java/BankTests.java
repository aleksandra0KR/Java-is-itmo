import ru.aleksandra0KR.bank.Entity.Account.BankAccount;
import ru.aleksandra0KR.bank.Entity.Account.CreditAccount;
import ru.aleksandra0KR.bank.Entity.Account.DebitAccount;
import ru.aleksandra0KR.bank.Entity.Account.DepositAccount;
import ru.aleksandra0KR.bank.Entity.Bank.Bank;
import ru.aleksandra0KR.bank.Entity.Bank.BankBuilder;
import ru.aleksandra0KR.bank.Entity.Bank.CentralBank;
import ru.aleksandra0KR.bank.Tools.Status;
import ru.aleksandra0KR.bank.Entity.Transaction.Replenishment;
import ru.aleksandra0KR.bank.Entity.Transaction.Transfer;
import ru.aleksandra0KR.bank.Entity.User.User;
import ru.aleksandra0KR.bank.Entity.User.UserBuilder;
import ru.aleksandra0KR.bank.Model.Transaction.Transaction;
import ru.aleksandra0KR.bank.Tools.DepositMoneyGapPercentage;
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
        Assertions.assertEquals(BigDecimal.valueOf(150000), debitAccount.getMoney());
    }

    @Test
    public void OpenDepositAccountGetPercentage() {
        CentralBank centralBank = CentralBank.getInstance();
        centralBank.AddTime(50);
        BigDecimal expected = new BigDecimal("16849.3151").setScale(10, RoundingMode.HALF_UP);
        BigDecimal actual = depositAccount.getMoney().setScale(10, RoundingMode.HALF_UP);
        assertEquals(expected, actual);
    }

    @Test
    public void MakeTransfer() {
        Transaction transaction = new Transfer(creditAccount, debitAccount, BigDecimal.valueOf(10000), Status.Created);
        transaction.execute();
        Assertions.assertEquals(BigDecimal.valueOf(990000), creditAccount.getMoney());
    }

    @Test
    public void Notifications() {
        bank.Attach(user);
        bank.Notify("Hello World!!!!!");

        Assertions.assertEquals("Hello World!!!!!", user.get_messages().get(0));
    }

    @Test
    public void CancelTransfer() {
        CentralBank centralBank = CentralBank.getInstance();
        var transactionID = centralBank.Transfer(creditAccount, debitAccount, BigDecimal.valueOf(10000));
        centralBank.CancelTransaction(transactionID);
        Assertions.assertEquals( BigDecimal.valueOf(1000000), creditAccount.getMoney());
    }

    @Test
    public void NotificationsDetach() {
        bank.Attach(user);
        bank.Notify("Hello World!!!!!");
        bank.Detach(user);
        bank.Notify("Hello World2.0!!!!!");

        Assertions.assertEquals(1, user.get_messages().size());
    }

    @Test
    public void ChangeCreditLimit() {
        bank.setCreditLimit(BigDecimal.valueOf(100000));
        Assertions.assertEquals(BigDecimal.valueOf(100000), bank.getCreditLimit());
    }

    @Test
    public void ShowBalance() {
        Assertions.assertEquals(BigDecimal.valueOf(50000),debitAccount.getMoney());
    }

    @Test
    public void AddDays() {
        CentralBank centralBank = CentralBank.getInstance();
        var time = centralBank.getBankCalendar();
        centralBank.AddTime(20);
        time = time.plusDays(20);
        Assertions.assertEquals(time, centralBank.getBankCalendar());
    }


}
