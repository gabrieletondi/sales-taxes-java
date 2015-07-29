package it.gabrieletondi.salestaxes;

import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;

public class PlainTextBasketFormatterTest {

    @Test
    public void multipleLinesBasket() throws Exception {
        Basket basket = new Basket(NeverlandTaxPolicyFactory.build());
        SaleItem firstItem = new SaleItem("item1", new BigDecimal("10.02"), 1, true);
        basket.add(firstItem);
        SaleItem secondItem = new SaleItem("item2", new BigDecimal("2.30"), 1, false);
        basket.add(secondItem);

        PlainTextBasketFormatter formatter = new PlainTextBasketFormatter();

        String expected = "1 imported item1: 11.62\n" +
                "1 item2: 2.55\n" +
                "Sales Taxes: 1.85\n" +
                "Total: 14.17";

        assertEquals(expected, formatter.format(basket));
    }
}
