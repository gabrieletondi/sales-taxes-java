package it.gabrieletondi.salestaxes;

public class POS {

    private final Display display;
    private Basket basket;

    public POS(TaxPolicy taxPolicy, Display display) {
        this.display = display;
        this.basket = new Basket(taxPolicy);
    }

    public void onSellCommand(String sellCommand) {
        SaleItem saleItem = SaleItem.fromSellCommand(sellCommand);
        basket.add(saleItem);
    }

    public void onSaleComplete() {
        display.show(new PlainTextBasketFormatter().format(basket));
    }

}
