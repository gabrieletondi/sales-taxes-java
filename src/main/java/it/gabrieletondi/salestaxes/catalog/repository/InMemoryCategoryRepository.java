package it.gabrieletondi.salestaxes.catalog.repository;

import it.gabrieletondi.salestaxes.catalog.Category;

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
