package it.gabrieletondi.salestaxes.doubles;

import it.gabrieletondi.salestaxes.catalog.Category;
import it.gabrieletondi.salestaxes.catalog.repository.CategoryRepository;

public class FixedCategoryRepository implements CategoryRepository {
    private final Category category;

    public FixedCategoryRepository(Category fixedCategory) {
        this.category = fixedCategory;
    }

    public Category ofProduct(String productName) {
        return category;
    }
}
