package ru.aleksandra0KR.bank.Entity.Bank;

import ru.aleksandra0KR.bank.Model.Account.Account;
import ru.aleksandra0KR.bank.Tools.DepositMoneyGapPercentage;

import java.math.BigDecimal;
import java.util.List;

/**
 * Class for building a bank
 * @author Aleksandra0KR
 * @version 1.0
 */
public class BankBuilder {
    private Bank Bank;

    /**
     * Resets the Bank object to a new instance.
     */
    public void Reset(){

        Bank = new Bank();
    }

    /**
     * Constructs a new BankBuilder instance and resets it to initialize a new Bank object.
     */
    public BankBuilder(){

        this.Reset();
    }

    /**
     * Sets the name of the bank.
     *
     * @param name the name of the bank
     */
    public void SetBankName(String name){

        Bank.setName(name);
    }

    /**
     * Sets the account for the bank.
     *
     * @param account the account of the bank
     */
    public void SetBankAccount(Account account){

        Bank.setAccount(account);
    }

    /**
     * Sets the debit percentage for the bank.
     *
     * @param debitPercentage the percentage for debit accounts
     */
    public void SetDebitPercentage(BigDecimal debitPercentage){

        Bank.setDebitPercentage(debitPercentage);
    }

    /**
     * Sets the list of deposit percentage gaps for the bank.
     *
     * @param depositPercentage a list of DepositMoneyGapPercentage objects representing deposit percentage gaps
     */
    public void SetDepositPercentage(List<DepositMoneyGapPercentage> depositPercentage){
        Bank.setDepositPercentage(depositPercentage);
    }

    /**
     * Sets the credit limit and commission rules for the bank.
     *
     * @param creditLimit the maximum credit limit allowed
     * @param creditCommission the commission for credit accounts
     */
    public void SetCreditRules(BigDecimal creditLimit, BigDecimal creditCommission){

        Bank.setCreditLimit(creditLimit);
        Bank.setCreditCommission(creditCommission);
    }

    /**
     * Sets the untrusted limit for the bank.
     *
     * @param untrustedLimit the limit for transactions with users without passport or address
     */
    public void SetUntrustedRules(BigDecimal untrustedLimit){

        Bank.setUntrustedLimit(untrustedLimit);
    }

    /**
     * Returns the Bank object, assigns it to its account, resets itself, and returns the Bank object.
     *
     * @return a fully configured Bank object with an associated account
     */
    public Bank GetBank(){
        Bank bank = this.Bank;
        bank.getAccount().setBank(bank);
        this.Reset();
        return bank;
    }



}
