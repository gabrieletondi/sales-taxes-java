package it.gabrieletondi.salestaxes.tax;

import it.gabrieletondi.salestaxes.catalog.CartItem;
import it.gabrieletondi.salestaxes.catalog.Category;
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
        Tax tax = policy.forItem(notImportedItemWithCategory(Category.MISC));

        Assert.assertEquals(PercentageTax.withRate(10, new NearestToFiveCentsRounding()), tax);
    }

    @Test
    public void foodIsExempt() throws Exception {
        Tax tax = policy.forItem(notImportedItemWithCategory(Category.FOOD));
        assertEquals(PercentageTax.EXEMPT, tax);
    }

    @Test
    public void booksAreExempt() throws Exception {
        Tax tax = policy.forItem(notImportedItemWithCategory(Category.BOOKS));
        assertEquals(PercentageTax.EXEMPT, tax);
    }

    @Test
    public void medicalProductsAreExempt() throws Exception {
        Tax tax = policy.forItem(notImportedItemWithCategory(Category.MEDICALS));
        assertEquals(PercentageTax.EXEMPT, tax);
    }

    @Test
    public void importedItemsHaveAdditionalTax() throws Exception {
        Tax tax = policy.forItem(importedItemWithCategory(Category.MISC));

        Tax expectedTax = new CompositeTax(
                PercentageTax.withRate(10, new NearestToFiveCentsRounding()),
                PercentageTax.withRate(5, new NearestToFiveCentsRounding())
        );
        assertEquals(expectedTax, tax);
    }

    private CartItem importedItemWithCategory(Category category) {
        return new CartItem(null, null, 0, true, category);
    }

    private CartItem notImportedItemWithCategory(Category category) {
        return new CartItem(null, null, 0, false, category);
    }
}
