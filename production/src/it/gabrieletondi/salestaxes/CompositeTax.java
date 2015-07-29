package it.gabrieletondi.salestaxes;

import java.math.BigDecimal;
import java.util.Arrays;

public class CompositeTax implements Tax {
    private final Tax[] taxes;

    public CompositeTax(Tax... taxes) {
        this.taxes = taxes;
    }

    public BigDecimal dutyAmount(BigDecimal netPrice) {
        BigDecimal dutyAmount = BigDecimal.ZERO;

        for(Tax tax:taxes)
            dutyAmount = dutyAmount.add(tax.dutyAmount(netPrice));

        return dutyAmount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CompositeTax that = (CompositeTax) o;

        return Arrays.equals(taxes, that.taxes);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(taxes);
    }

    @Override
    public String toString() {
        String result = "";

        for (Tax t: taxes)
            result += t.toString();

        return result;
    }
}
