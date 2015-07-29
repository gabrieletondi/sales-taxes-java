package it.gabrieletondi.salestaxes.receipt;

import it.gabrieletondi.salestaxes.catalog.CartItem;
import it.gabrieletondi.salestaxes.catalog.Category;
import it.gabrieletondi.salestaxes.doubles.FixedTaxAmount;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;

public class ReceiptItemTest {

    private static final BigDecimal TAX_AMOUNT = BigDecimal.ONE;

    @Test
    public void buildFromCartItem() throws Exception {
        CartItem cartItem = new CartItem("product name", new BigDecimal("1.23"), 2, true, Category.MISC);
        ReceiptItem receiptItem = ReceiptItem.from(cartItem, new FixedTaxAmount(TAX_AMOUNT));

        assertEquals(cartItem.getProductName(), receiptItem.getProductName());
        assertEquals(new BigDecimal("4.46"), receiptItem.getTaxedPrice());
        assertEquals(cartItem.isImported(), receiptItem.isImported());
        assertEquals(cartItem.getQuantity(), receiptItem.getQuantity());
        assertEquals(new BigDecimal("2"), receiptItem.getTaxDuty());
    }
}
