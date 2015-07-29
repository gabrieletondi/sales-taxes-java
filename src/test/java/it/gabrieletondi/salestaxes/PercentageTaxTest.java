package it.gabrieletondi.salestaxes;

import it.gabrieletondi.salestaxes.doubles.FixedValueRounding;
import it.gabrieletondi.salestaxes.tax.PercentageTax;
import it.gabrieletondi.salestaxes.tax.rounding.Rounding;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;

public class PercentageTaxTest {

    @Test
    public void dutyOnNetPrice() throws Exception {
        PercentageTax tax = PercentageTax.withRate(15, Rounding.NONE);
        BigDecimal taxAmount = tax.dutyAmount(new BigDecimal("12.35"));

        assertEquals(new BigDecimal("1.8525"), taxAmount);
    }

    @Test
    public void applyRounding() throws Exception {
        PercentageTax tax = PercentageTax.withRate(10, new FixedValueRounding(1));
        BigDecimal taxAmount = tax.dutyAmount(new BigDecimal("0.234"));

        assertEquals(new BigDecimal("1"), taxAmount);
    }

}
