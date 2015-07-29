package it.gabrieletondi.salestaxes;

import it.gabrieletondi.salestaxes.catalog.Category;
import it.gabrieletondi.salestaxes.catalog.ShelfItem;
import it.gabrieletondi.salestaxes.tax.*;
import it.gabrieletondi.salestaxes.tax.rounding.NearestToFiveCentsRounding;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class NeverlandTaxPolicyFactoryTest {

    private TaxPolicy policy;

    @Before
    public void setUp() {
        policy = NeverlandTaxPolicyFactory.build();
    }

    @Test
    public void defaultRateIs10() throws Exception {
        ShelfItem item = new ShelfItem(null, null, 0, false, Category.MISC);
        Tax tax = policy.forItem(item);

        Assert.assertEquals(PercentageTax.withRate(10, new NearestToFiveCentsRounding()), tax);
    }

    @Test
    public void foodIsExempt() throws Exception {
        ShelfItem item = new ShelfItem(null, null, 0, false, Category.FOOD);
        Tax tax = policy.forItem(item);
        assertEquals(PercentageTax.EXEMPT, tax);
    }

    @Test
    public void booksAreExempt() throws Exception {
        ShelfItem item = new ShelfItem(null, null, 0, false, Category.BOOKS);
        Tax tax = policy.forItem(item);
        assertEquals(PercentageTax.EXEMPT, tax);
    }

    @Test
    public void medicalProductsAreExempt() throws Exception {
        ShelfItem item = new ShelfItem(null, null, 0, false, Category.MEDICALS);
        Tax tax = policy.forItem(item);
        assertEquals(PercentageTax.EXEMPT, tax);
    }

    @Test
    public void importedItemsHaveAdditionalTax() throws Exception {
        ShelfItem item = new ShelfItem(null, null, 0, true, Category.MISC);
        Tax tax = policy.forItem(item);

        Tax expectedTax = new CompositeTax(PercentageTax.withRate(10, new NearestToFiveCentsRounding()), PercentageTax.withRate(5, new NearestToFiveCentsRounding()));
        assertEquals(expectedTax, tax);
    }
}
