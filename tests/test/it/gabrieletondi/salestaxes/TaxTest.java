package it.gabrieletondi.salestaxes;

import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;

public class TaxTest {

    @Test
    public void applyOnPrice() throws Exception {
        Tax tax = Tax.withRate(15);
        BigDecimal taxAmount = tax.applyTo(new BigDecimal("12.35"));

        assertEquals(new BigDecimal("1.8525"), taxAmount);
    }

}
