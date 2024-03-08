package ru.aleksandra0KR.bank.Tools;

import java.math.BigDecimal;

/**
 * Class for defining deposit money gap percentage ranges
 * @author Aleksandra0KR
 * @version 1.0
 */
public class DepositMoneyGapPercentage {

    /**
     * Constructor for defining a deposit money gap percentage range.
     * @param lowerBoundary The lower boundary of the deposit money range.
     * @param topBoundary The top boundary of the deposit money range.
     * @param percentage The percentage associated with the deposit money range.
     */
    public DepositMoneyGapPercentage(BigDecimal lowerBoundary, BigDecimal topBoundary, BigDecimal percentage) {
        LowerBoundary = lowerBoundary;
        TopBoundary = topBoundary;
        Percentage = percentage;
    }
    public BigDecimal LowerBoundary; // The lower boundary of the deposit money range.
    public BigDecimal TopBoundary; // The top boundary of the deposit money range.
    public BigDecimal Percentage; // The percentage associated with the deposit money range.

    /**
     Checks if a given amount of money falls within the defined deposit money range.
     @param money The amount of money to check.
     @return true if the amount falls within the range, false otherwise.
     */
    public Boolean SuitedPercentage(BigDecimal money)
    {
        return money.compareTo(LowerBoundary) >= 0 && money.compareTo(TopBoundary) <= 0;
    }
}