package it.gabrieletondi.salestaxes;

import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class BasketTest {

    private Basket basket;

    @Before
    public void setUp() throws Exception {
        basket = new Basket(NeverlandTaxPolicyFactory.build());
    }

    @Test
    public void emptyBasket() throws Exception {
        Basket basket = new Basket(NeverlandTaxPolicyFactory.build());

        assertEquals(BigDecimal.ZERO, basket.getTotal());
        assertEquals(BigDecimal.ZERO, basket.getSalesTaxes());
    }

    @Test
    public void addItemsToBasket() throws Exception {
        basket.add(new SaleItem("product 1", new BigDecimal("3.44"), 1, false));
        basket.add(new SaleItem("product 2", new BigDecimal("0.35"), 1, true));

        List<BasketItem> items = basket.getItems();

        assertEquals(2, items.size());
        BasketItem firstItem = items.get(0);
        BasketItem secondItem = items.get(1);

        assertEquals(new BigDecimal("3.79"), firstItem.getTaxedPrice());
        assertEquals(1, firstItem.getQuantity());
        assertEquals("product 1", firstItem.getProductName());

        assertEquals(new BigDecimal("0.45"), secondItem.getTaxedPrice());
        assertEquals(1, secondItem.getQuantity());
        assertEquals("product 2", secondItem.getProductName());
        assertTrue(secondItem.isImported());
    }

    @Test
    public void calculatesTotals() throws Exception {
        basket.add(new SaleItem("product 1", new BigDecimal("12.34"), 1, false));
        basket.add(new SaleItem("product 2", new BigDecimal("2.23"), 1, false));

        assertEquals(new BigDecimal("16.07"), basket.getTotal());
        assertEquals(new BigDecimal("1.50"), basket.getSalesTaxes());
    }
}
