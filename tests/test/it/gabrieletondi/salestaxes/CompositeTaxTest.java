package it.gabrieletondi.salestaxes;

import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;

public class CompositeTaxTest {

    @Test
    public void applyTaxAlwaysOnNetPrice() throws Exception {
        CompositeTax tax = new CompositeTax(PercentageTax.withRate(10, new NearestToFiveCentsRounding()), PercentageTax.withRate(5, new NearestToFiveCentsRounding()));

        BigDecimal dutyAmount = tax.dutyAmount(new BigDecimal("47.50"));

        assertEquals(new BigDecimal("7.15"), dutyAmount);
    }
}
