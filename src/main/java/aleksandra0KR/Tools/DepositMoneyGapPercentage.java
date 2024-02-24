package aleksandra0KR.Tools;

import java.math.BigDecimal;

public class DepositMoneyGapPercentage {
    public DepositMoneyGapPercentage(BigDecimal lowerBoundary, BigDecimal topBoundary, BigDecimal percentage) {
        LowerBoundary = lowerBoundary;
        TopBoundary = topBoundary;
        Percentage = percentage;
    }
    public BigDecimal LowerBoundary;
    public BigDecimal TopBoundary;
    public BigDecimal Percentage;

    public Boolean SuitedPercentage(BigDecimal money)
    {
        return money.compareTo(LowerBoundary) >= 0 && money.compareTo(TopBoundary) <= 0;
    }
}
