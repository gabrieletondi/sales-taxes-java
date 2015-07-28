package it.gabrieletondi.salestaxes;

import it.gabrieletondi.salestaxes.doubles.FixedValueRounding;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;

public class TaxTest {

    @Test
    public void dutyOnNetPrice() throws Exception {
        Tax tax = Tax.withRate(15, Rounding.NONE);
        BigDecimal taxAmount = tax.dutyAmount(new BigDecimal("12.35"));

        assertEquals(new BigDecimal("1.8525"), taxAmount);
    }

    @Test
    public void applyRounding() throws Exception {
        Tax tax = Tax.withRate(10, new FixedValueRounding(1));
        BigDecimal taxAmount = tax.dutyAmount(new BigDecimal("0.234"));

        assertEquals(new BigDecimal("1"), taxAmount);
    }

}
