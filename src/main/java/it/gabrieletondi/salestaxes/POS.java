package it.gabrieletondi.salestaxes;

public class POS {

    private final Display display;
    private Receipt receipt;

    public POS(TaxPolicy taxPolicy, Display display) {
        this.display = display;
        this.receipt = new Receipt(taxPolicy);
    }

    public void onSellCommand(String sellCommand) {
        ShelfItem shelfItem = ShelfItem.fromSellCommand(sellCommand);
        receipt.add(shelfItem);
    }

    public void onSaleComplete() {
        display.show(new PlainTextReceiptFormatter().format(receipt));
    }

}
