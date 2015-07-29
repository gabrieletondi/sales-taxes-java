package it.gabrieletondi.salestaxes;

import it.gabrieletondi.salestaxes.catalog.Category;
import it.gabrieletondi.salestaxes.catalog.ShelfItem;
import it.gabrieletondi.salestaxes.tax.ByCategoryAndImportTaxPolicy;
import it.gabrieletondi.salestaxes.tax.CompositeTax;
import it.gabrieletondi.salestaxes.tax.PercentageTax;
import it.gabrieletondi.salestaxes.tax.Tax;
import it.gabrieletondi.salestaxes.tax.rounding.Rounding;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ByCategoryAndImportTaxPolicyTest {

    public static final PercentageTax DEFAULT_TAX = PercentageTax.withRate(10, Rounding.NONE);
    private static final PercentageTax IMPORTED_TAX = PercentageTax.withRate(2, Rounding.NONE);

    @Test
    public void defaultRate() throws Exception {
        ByCategoryAndImportTaxPolicy policy = new ByCategoryAndImportTaxPolicy(DEFAULT_TAX, IMPORTED_TAX);

        ShelfItem item = new ShelfItem("default rate item", null, 1, false, null);
        Tax actualTax = policy.forItem(item);

        assertEquals(DEFAULT_TAX, actualTax);
    }

    @Test
    public void exemptCategory() throws Exception {
        ByCategoryAndImportTaxPolicy policy = new ByCategoryAndImportTaxPolicy(DEFAULT_TAX, IMPORTED_TAX, Category.BOOKS);

        ShelfItem item = new ShelfItem(null, null, 0, false, Category.BOOKS);
        Tax actualTax = policy.forItem(item);

        assertEquals(PercentageTax.EXEMPT, actualTax);
    }

    @Test
    public void additionalTaxOnImportedItems() throws Exception {
        ByCategoryAndImportTaxPolicy policy = new ByCategoryAndImportTaxPolicy(DEFAULT_TAX, IMPORTED_TAX);

        ShelfItem item = new ShelfItem(null, null, 1, true, Category.MISC);
        Tax actualTax = policy.forItem(item);

        assertEquals(new CompositeTax(DEFAULT_TAX, IMPORTED_TAX), actualTax);
    }
}
