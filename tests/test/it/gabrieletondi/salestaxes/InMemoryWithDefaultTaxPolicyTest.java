package it.gabrieletondi.salestaxes;

import org.junit.Test;

import java.util.HashMap;

import static org.junit.Assert.assertEquals;

public class InMemoryWithDefaultTaxPolicyTest {

    public static final PercentageTax DEFAULT_TAX = PercentageTax.withRate(10, Rounding.NONE);
    public static final PercentageTax SPECIFIC_TAX = PercentageTax.withRate(15, Rounding.NONE);

    @Test
    public void defaultRate() throws Exception {
        InMemoryWithDefaultTaxPolicy policy = new InMemoryWithDefaultTaxPolicy(DEFAULT_TAX, new HashMap<String, PercentageTax>());

        SaleItem item = new SaleItem("default rate item", null, 1, false);
        Tax actualTax = policy.forItem(item);

        assertEquals(DEFAULT_TAX, actualTax);
    }

    @Test
    public void specificRule() throws Exception {
        HashMap<String, PercentageTax> specificRules = new HashMap<String, PercentageTax>();
        specificRules.put("specific product", SPECIFIC_TAX);
        InMemoryWithDefaultTaxPolicy policy = new InMemoryWithDefaultTaxPolicy(DEFAULT_TAX, specificRules);

        SaleItem item = new SaleItem("specific product", null, 1, false);
        Tax actualTax = policy.forItem(item);

        assertEquals(SPECIFIC_TAX, actualTax);
    }
}
