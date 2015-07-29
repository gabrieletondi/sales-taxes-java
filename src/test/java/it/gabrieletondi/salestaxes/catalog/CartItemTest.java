package it.gabrieletondi.salestaxes.catalog;

import it.gabrieletondi.salestaxes.catalog.repository.CategoryRepository;
import it.gabrieletondi.salestaxes.doubles.AllOfAKindCategoryRepository;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class CartItemTest {

    private CategoryRepository categoryRepository;

    @Before
    public void setUp() throws Exception {
        categoryRepository = new AllOfAKindCategoryRepository(Category.MISC);
    }

    @Test
    public void singleWordItem() throws Exception {
        CartItem item = CartItem.fromSellCommand("1 book at 12.49", categoryRepository);

        assertEquals(1, item.getQuantity());
        assertEquals("book", item.getProductName());
        assertEquals(new BigDecimal("12.49"), item.getNetPrice());
        assertEquals(Category.MISC, item.getCategory());
    }

    @Test
    public void multipleWordItem() throws Exception {
        CartItem item = CartItem.fromSellCommand("1 item with multiple words at 12.49", categoryRepository);

        assertEquals(1, item.getQuantity());
        assertEquals("item with multiple words", item.getProductName());
        assertEquals(new BigDecimal("12.49"), item.getNetPrice());
    }

    @Test
    public void importedItem() throws Exception {
        CartItem item = CartItem.fromSellCommand("1 box of imported chocolates at 11.25", categoryRepository);

        assertEquals(1, item.getQuantity());
        assertEquals("box of chocolates", item.getProductName());
        assertEquals(new BigDecimal("11.25"), item.getNetPrice());
        assertTrue(item.isImported());
    }

    @Test
    public void importedItemStartingWithImported() throws Exception {
        CartItem item = CartItem.fromSellCommand("1 imported bottle of perfume at 32.19", categoryRepository);

        assertEquals(1, item.getQuantity());
        assertEquals("bottle of perfume", item.getProductName());
        assertEquals(new BigDecimal("32.19"), item.getNetPrice());
        assertTrue(item.isImported());
    }

    @Test
    public void multipleDigitQuantity() throws Exception {
        CartItem item = CartItem.fromSellCommand("1355 book at 12.49", categoryRepository);

        assertEquals(1355, item.getQuantity());
        assertEquals("book", item.getProductName());
        assertEquals(new BigDecimal("12.49"), item.getNetPrice());
    }
}
