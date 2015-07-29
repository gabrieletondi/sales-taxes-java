package it.gabrieletondi.salestaxes;

public class NeverlandTaxPolicyFactory {

    private static final PercentageTax IMPORTED_TAX = PercentageTax.withRate(5, new NearestToFiveCentsRounding());
    private static final PercentageTax DEFAULT_TAX = PercentageTax.withRate(10, new NearestToFiveCentsRounding());
    private static final Category[] EXEMPT_CATEGORIES = new Category[]{Category.BOOKS, Category.MEDICALS, Category.FOOD};

    public static TaxPolicy build() {
        return new ByCategoryAndImportTaxPolicy(DEFAULT_TAX, IMPORTED_TAX, EXEMPT_CATEGORIES);
    }
}
