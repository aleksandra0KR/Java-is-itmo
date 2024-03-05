package aleksandra0KR.Entity.Bank;

import aleksandra0KR.Model.Account.Account;
import aleksandra0KR.Tools.DepositMoneyGapPercentage;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;

public class BankBuilder {
    private Bank Bank;

    public void Reset(){
        Bank = new Bank();
    }

    public BankBuilder(){
        this.Reset();
    }

    public void SetBankName(String name){
        Bank.Name = name;
    }

    public void SetBankAccount(Account account){
        Bank.Account = account;
    }

    public void SetDebitPercentage(BigDecimal debitPercentage){

        Bank.setDebitPercentage(debitPercentage);
    }

    public void SetDepositPercentage(List<DepositMoneyGapPercentage> depositPercentage){
        Bank.setDepositPercentage(depositPercentage);
    }

    public void SetCreditRules(BigDecimal creditLimit, BigDecimal creditCommission){

        Bank.setCreditLimit(creditLimit);
        Bank.setCreditCommission(creditCommission);
    }

    public void SetUntrustedRules(BigDecimal untrustedLimit){
        Bank.setUntrustedLimit(untrustedLimit);
    }

    public Bank GetBank(){
        Bank bank = this.Bank;
        bank.Account.Bank = bank;
        this.Reset();
        return bank;
    }



}
