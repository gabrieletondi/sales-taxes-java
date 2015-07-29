package it.gabrieletondi.salestaxes;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TaxPolicyTest {

    public static final PercentageTax DEFAULT_TAX = PercentageTax.withRate(10, Rounding.NONE);
    private static final PercentageTax IMPORTED_TAX = PercentageTax.withRate(2, Rounding.NONE);

    @Test
    public void defaultRate() throws Exception {
        TaxPolicy policy = new TaxPolicy(DEFAULT_TAX, IMPORTED_TAX);

        ShelfItem item = new ShelfItem("default rate item", null, 1, false, null);
        Tax actualTax = policy.forItem(item);

        assertEquals(DEFAULT_TAX, actualTax);
    }

    @Test
    public void exemptCategory() throws Exception {
        TaxPolicy policy = new TaxPolicy(DEFAULT_TAX, IMPORTED_TAX, Category.BOOKS);

        ShelfItem item = new ShelfItem(null, null, 0, false, Category.BOOKS);
        Tax actualTax = policy.forItem(item);

        assertEquals(PercentageTax.EXEMPT, actualTax);
    }

    @Test
    public void additionalTaxOnImportedItems() throws Exception {
        TaxPolicy policy = new TaxPolicy(DEFAULT_TAX, IMPORTED_TAX);

        ShelfItem item = new ShelfItem(null, null, 1, true, Category.MISC);
        Tax actualTax = policy.forItem(item);

        assertEquals(new CompositeTax(DEFAULT_TAX, IMPORTED_TAX), actualTax);
    }
}
