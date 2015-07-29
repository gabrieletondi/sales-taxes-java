package it.gabrieletondi.salestaxes;

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
        ShelfItem shelfItem = ShelfItem.fromSellCommand(sellCommand, categoryRepository);
        receipt.add(shelfItem);
    }

    public void onSaleComplete() {
        display.show(new PlainTextReceiptFormatter().format(receipt));
    }

}
