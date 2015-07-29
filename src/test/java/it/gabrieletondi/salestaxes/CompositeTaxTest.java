package it.gabrieletondi.salestaxes;

import it.gabrieletondi.salestaxes.tax.CompositeTax;
import it.gabrieletondi.salestaxes.tax.PercentageTax;
import it.gabrieletondi.salestaxes.tax.rounding.NearestToFiveCentsRounding;
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
