package it.gabrieletondi.salestaxes;

import it.gabrieletondi.salestaxes.catalog.CartItem;
import it.gabrieletondi.salestaxes.catalog.repository.CategoryRepository;
import it.gabrieletondi.salestaxes.receipt.PlainTextReceiptFormatter;
import it.gabrieletondi.salestaxes.receipt.Receipt;
import it.gabrieletondi.salestaxes.tax.TaxPolicy;

public class POS {

    private final Display display;
    private final CategoryRepository categoryRepository;
    private Receipt receipt;

    public POS(TaxPolicy taxPolicy, Display display, CategoryRepository categoryRepository) {
        this.display = display;
        this.categoryRepository = categoryRepository;
        this.receipt = new Receipt(taxPolicy);
    }

    public void onSellCommand(String sellCommand) {
        CartItem cartItem = CartItem.fromSellCommand(sellCommand, categoryRepository);
        receipt.add(cartItem);
    }

    public void onSaleComplete() {
        display.show(new PlainTextReceiptFormatter().format(receipt));
    }

}
