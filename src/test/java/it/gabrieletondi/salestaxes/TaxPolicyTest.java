package it.gabrieletondi.salestaxes;

import org.junit.Test;

import java.util.HashMap;

import static org.junit.Assert.assertEquals;

public class TaxPolicyTest {

    public static final PercentageTax DEFAULT_TAX = PercentageTax.withRate(10, Rounding.NONE);
    public static final PercentageTax SPECIFIC_TAX = PercentageTax.withRate(15, Rounding.NONE);
    private static final PercentageTax IMPORTED_TAX = PercentageTax.withRate(2, Rounding.NONE);

    @Test
    public void defaultRate() throws Exception {
        TaxPolicy policy = new TaxPolicy(DEFAULT_TAX, new HashMap<String, PercentageTax>(), IMPORTED_TAX);

        ShelfItem item = new ShelfItem("default rate item", null, 1, false);
        Tax actualTax = policy.forItem(item);

        assertEquals(DEFAULT_TAX, actualTax);
    }

    @Test
    public void specificRule() throws Exception {
        HashMap<String, PercentageTax> specificRules = new HashMap<String, PercentageTax>();
        specificRules.put("specific product", SPECIFIC_TAX);
        TaxPolicy policy = new TaxPolicy(DEFAULT_TAX, specificRules, IMPORTED_TAX);

        ShelfItem item = new ShelfItem("specific product", null, 1, false);
        Tax actualTax = policy.forItem(item);

        assertEquals(SPECIFIC_TAX, actualTax);
    }

    @Test
    public void additionalTaxOnImportedItems() throws Exception {
        TaxPolicy policy = new TaxPolicy(DEFAULT_TAX, new HashMap<String, PercentageTax>(), IMPORTED_TAX);

        ShelfItem item = new ShelfItem("default rate item", null, 1, true);
        Tax actualTax = policy.forItem(item);

        assertEquals(new CompositeTax(DEFAULT_TAX, IMPORTED_TAX), actualTax);
    }
}
