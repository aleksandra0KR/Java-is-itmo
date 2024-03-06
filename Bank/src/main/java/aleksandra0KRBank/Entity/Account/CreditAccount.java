package aleksandra0KRBank.Entity.Account;

import aleksandra0KRBank.Entity.Transaction.Transfer;
import aleksandra0KRBank.Exceptions.NotEnoughMoneyException;
import aleksandra0KRBank.Entity.Status.Status;
import aleksandra0KRBank.Model.Account.Account;
import aleksandra0KRBank.Entity.User.User;
import aleksandra0KRBank.Entity.Bank.Bank;
import aleksandra0KRBank.Model.Transaction.Transaction;

import java.math.BigDecimal;
import java.util.Calendar;

/**
 * Class for the credit account
 * It extends the Account class and includes methods for managing money transactions and calculations.
 * @author Aleksandra0KR
 * @version 1.0
 */
public class CreditAccount extends Account {
    private BigDecimal CreditLimit;

    /**
     * Constructs a CreditAccount object with the specified parameters.
     *
     * @param money the initial amount of money in the account
     * @param openDate the date when the account was opened
     * @param closeDate the date when the account will be closed
     * @param user the user associated with the account
     * @param bank the bank associated with the account
     * @param creditLimit the maximum credit limit allowed for the account
     * @param commission the commission charged for using the account
     */
    public CreditAccount(BigDecimal money, Calendar openDate, Calendar closeDate, User user, Bank bank, BigDecimal creditLimit, BigDecimal commission){
        super(money, openDate, closeDate, user,BigDecimal.ZERO, commission);
        CreditLimit = creditLimit;
        Bank = bank;
    }

    /**
     * Calculates daily percentage based on the number of days. CreditAccount doesn't have percentage
     *
     * @param days the number of days for which to calculate the daily percentage
     */
    @Override
    public void DailyPercentage(int days) {}

    /**
     * Calculates the monthly profit for the account. CreditAccount doesn't have percentage
     *
     * @return a Transaction object representing the monthly profit operation
     */
    @Override
    public Transaction MonthlyProfit() {
        return null;
    }

    /**
     * Calculates the monthly commission for the account.
     *
     * @return a Transaction object representing the monthly commission operation
     */
    public Transaction MonthlyCommission()
    {
        if(Money.compareTo(BigDecimal.ZERO) < 0) {
            return (new Transfer(this, Bank.Account, Commission, Status.Created));
        }
        return null;
    }

    /**
     * Takes off a specified amount of money from the account balance.
     *
     * @param money the amount to be subtracted from the account balance
     * @throws NotEnoughMoneyException customized exception if there is not enough money in the account to perform the transaction
     */
    @Override
    public void TakeOffMoney(BigDecimal money) {
        if(Money.compareTo(CreditLimit) < 0) throw new NotEnoughMoneyException();
        Money = Money.subtract(money);
    }
}
