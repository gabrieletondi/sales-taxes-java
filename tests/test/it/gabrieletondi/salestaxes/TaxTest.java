package it.gabrieletondi.salestaxes;

import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;

public class TaxTest {

    @Test
    public void dutyOnNetPrice() throws Exception {
        Tax tax = Tax.withRate(15);
        BigDecimal taxAmount = tax.dutyAmount(new BigDecimal("12.35"));

        assertEquals(new BigDecimal("1.8525"), taxAmount);
    }

}
