package it.gabrieletondi.salestaxes.receipt;

import it.gabrieletondi.salestaxes.catalog.CartItem;
import it.gabrieletondi.salestaxes.catalog.Category;
import it.gabrieletondi.salestaxes.tax.PercentageTax;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;

public class ReceiptItemTest {

    @Test
    public void buildFromCartItem() throws Exception {
        CartItem cartItem = new CartItem("product name", new BigDecimal("1.23"), 2, true, Category.MISC);
        ReceiptItem receiptItem = ReceiptItem.from(cartItem, PercentageTax.EXEMPT);

        assertEquals(cartItem.getProductName(), receiptItem.getProductName());
        assertEquals(cartItem.getNetPrice().multiply(BigDecimal.valueOf(2)), receiptItem.getTaxedPrice());
        assertEquals(cartItem.isImported(), receiptItem.isImported());
        assertEquals(cartItem.getQuantity(), receiptItem.getQuantity());
    }
}
