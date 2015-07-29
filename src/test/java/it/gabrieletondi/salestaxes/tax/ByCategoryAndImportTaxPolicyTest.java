package it.gabrieletondi.salestaxes.tax;

import it.gabrieletondi.salestaxes.catalog.CartItem;
import it.gabrieletondi.salestaxes.catalog.Category;
import it.gabrieletondi.salestaxes.tax.rounding.Rounding;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ByCategoryAndImportTaxPolicyTest {

    public static final PercentageTax DEFAULT_TAX = PercentageTax.withRate(10, Rounding.NONE);
    private static final PercentageTax IMPORTED_TAX = PercentageTax.withRate(2, Rounding.NONE);

    @Test
    public void defaultRate() throws Exception {
        ByCategoryAndImportTaxPolicy policy = new ByCategoryAndImportTaxPolicy(DEFAULT_TAX, IMPORTED_TAX);

        CartItem item = new CartItem("default rate item", null, 1, false, null);
        Tax actualTax = policy.forItem(item);

        assertEquals(DEFAULT_TAX, actualTax);
    }

    @Test
    public void exemptCategory() throws Exception {
        ByCategoryAndImportTaxPolicy policy = new ByCategoryAndImportTaxPolicy(DEFAULT_TAX, IMPORTED_TAX, Category.BOOKS);

        CartItem item = new CartItem(null, null, 0, false, Category.BOOKS);
        Tax actualTax = policy.forItem(item);

        assertEquals(PercentageTax.EXEMPT, actualTax);
    }

    @Test
    public void additionalTaxOnImportedItems() throws Exception {
        ByCategoryAndImportTaxPolicy policy = new ByCategoryAndImportTaxPolicy(DEFAULT_TAX, IMPORTED_TAX);

        CartItem item = new CartItem(null, null, 1, true, Category.MISC);
        Tax actualTax = policy.forItem(item);

        assertEquals(new CompositeTax(DEFAULT_TAX, IMPORTED_TAX), actualTax);
    }
}
