package it.gabrieletondi.salestaxes;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class NeverlandTaxPolicyFactoryTest {

    @Test
    public void defaultRateIs10() throws Exception {
        InMemoryWithDefaultTaxPolicy policy = NeverlandTaxPolicyFactory.build();

        Tax tax = policy.forItemName("any standard item");

        assertEquals(Tax.withRate(10, new NearestToFiveCentsRounding()), tax);
    }

    @Test
    public void foodIsExempt() throws Exception {
        InMemoryWithDefaultTaxPolicy policy = NeverlandTaxPolicyFactory.build();

        Tax tax = policy.forItemName("chocolate bar");
        assertEquals(Tax.EXEMPT, tax);

        tax = policy.forItemName("box of chocolates");
        assertEquals(Tax.EXEMPT, tax);
    }

    @Test
    public void booksAreExempt() throws Exception {
        InMemoryWithDefaultTaxPolicy policy = NeverlandTaxPolicyFactory.build();

        Tax tax = policy.forItemName("book");
        assertEquals(Tax.EXEMPT, tax);
    }

    @Test
    public void medicalProductsAreExempt() throws Exception {
        InMemoryWithDefaultTaxPolicy policy = NeverlandTaxPolicyFactory.build();

        Tax tax = policy.forItemName("headache pills");
        assertEquals(Tax.EXEMPT, tax);
    }
}
