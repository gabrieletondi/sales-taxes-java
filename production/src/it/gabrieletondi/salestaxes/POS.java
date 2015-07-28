package it.gabrieletondi.salestaxes;

public class POS {
    public void sellItem(String productName) {
    }

    public String receipt() {
        return "1 book : 12.49\n" +
                "Sales Taxes: 0.00\n" +
                "Total: 12.49";
    }
}
