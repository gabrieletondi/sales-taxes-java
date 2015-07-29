package it.gabrieletondi.salestaxes;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;

public class NeverlandTaxPolicyFactoryTest {

    private TaxPolicy policy;

    @Before
    public void setUp() {
        policy = NeverlandTaxPolicyFactory.build();
    }

    @Test
    public void defaultRateIs10() throws Exception {
        ShelfItem item = new ShelfItem("any standard item", BigDecimal.TEN, 1, false);
        Tax tax = policy.forItem(item);

        Assert.assertEquals(PercentageTax.withRate(10, new NearestToFiveCentsRounding()), tax);
    }

    @Test
    public void foodIsExempt() throws Exception {
        ShelfItem item = new ShelfItem("chocolate bar", BigDecimal.TEN, 1, false);
        Tax tax = policy.forItem(item);
        assertEquals(PercentageTax.EXEMPT, tax);

        item = new ShelfItem("box of chocolates", BigDecimal.TEN, 1, false);
        tax = policy.forItem(item);
        assertEquals(PercentageTax.EXEMPT, tax);
    }

    @Test
    public void booksAreExempt() throws Exception {
        ShelfItem item = new ShelfItem("book", BigDecimal.TEN, 1, false);
        Tax tax = policy.forItem(item);
        assertEquals(PercentageTax.EXEMPT, tax);
    }

    @Test
    public void medicalProductsAreExempt() throws Exception {
        ShelfItem item = new ShelfItem("packet of headache pills", BigDecimal.TEN, 1, false);
        Tax tax = policy.forItem(item);
        assertEquals(PercentageTax.EXEMPT, tax);
    }

    @Test
    public void importedItemsHaveAdditionalTax() throws Exception {
        ShelfItem item = new ShelfItem("any standard item", BigDecimal.TEN, 1, true);
        Tax tax = policy.forItem(item);

        Tax expectedTax = new CompositeTax(PercentageTax.withRate(10, new NearestToFiveCentsRounding()), PercentageTax.withRate(5, new NearestToFiveCentsRounding()));
        assertEquals(expectedTax, tax);
    }
}
