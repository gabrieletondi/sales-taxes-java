package it.gabrieletondi.salestaxes;

import java.util.Map;

public class InMemoryCategoryRepository implements CategoryRepository {

    private final Map<String, Category> productsToCategoriesMapping;

    public InMemoryCategoryRepository(Map<String, Category> productsToCategoriesMapping) {
        this.productsToCategoriesMapping = productsToCategoriesMapping;
    }

    public Category ofProduct(String productName) {
        return productsToCategoriesMapping.get(productName);
    }
}
