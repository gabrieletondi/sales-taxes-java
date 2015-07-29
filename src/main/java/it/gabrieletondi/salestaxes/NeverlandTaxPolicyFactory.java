package it.gabrieletondi.salestaxes;

public class NeverlandTaxPolicyFactory {

    private static final PercentageTax IMPORTED_TAX = PercentageTax.withRate(5, new NearestToFiveCentsRounding());
    private static final PercentageTax DEFAULT_TAX = PercentageTax.withRate(10, new NearestToFiveCentsRounding());

    public static TaxPolicy build() {

        return new TaxPolicy(DEFAULT_TAX, IMPORTED_TAX, Category.BOOKS, Category.MEDICALS, Category.FOOD);
    }
}
