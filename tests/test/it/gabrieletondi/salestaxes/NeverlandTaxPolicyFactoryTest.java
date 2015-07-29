package it.gabrieletondi.salestaxes;

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
        SaleItem item = new SaleItem("any standard item", BigDecimal.TEN, 1, false);
        Tax tax = policy.forItem(item);

        assertEquals(PercentageTax.withRate(10, new NearestToFiveCentsRounding()), tax);
    }

    @Test
    public void foodIsExempt() throws Exception {
        SaleItem item = new SaleItem("chocolate bar", BigDecimal.TEN, 1, false);
        Tax tax = policy.forItem(item);
        assertEquals(PercentageTax.EXEMPT, tax);

        item = new SaleItem("box of chocolates", BigDecimal.TEN, 1, false);
        tax = policy.forItem(item);
        assertEquals(PercentageTax.EXEMPT, tax);
    }

    @Test
    public void booksAreExempt() throws Exception {
        SaleItem item = new SaleItem("book", BigDecimal.TEN, 1, false);
        Tax tax = policy.forItem(item);
        assertEquals(PercentageTax.EXEMPT, tax);
    }

    @Test
    public void medicalProductsAreExempt() throws Exception {
        SaleItem item = new SaleItem("packet of headache pills", BigDecimal.TEN, 1, false);
        Tax tax = policy.forItem(item);
        assertEquals(PercentageTax.EXEMPT, tax);
    }

    @Test
    public void importedItemsHaveAdditionalTax() throws Exception {
        SaleItem item = new SaleItem("any standard item", BigDecimal.TEN, 1, true);
        Tax tax = policy.forItem(item);

        Tax expectedTax = new CompositeTax(PercentageTax.withRate(10, new NearestToFiveCentsRounding()), PercentageTax.withRate(5, new NearestToFiveCentsRounding()));
        assertEquals(expectedTax, tax);
    }
}
