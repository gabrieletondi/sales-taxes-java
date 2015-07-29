package it.gabrieletondi.salestaxes.doubles;

import it.gabrieletondi.salestaxes.Category;
import it.gabrieletondi.salestaxes.CategoryRepository;

public class AllOfAKindCategoryRepository implements CategoryRepository {
    private final Category category;

    public AllOfAKindCategoryRepository(Category fixedCategory) {
        this.category = fixedCategory;
    }

    public Category ofProduct(String productName) {
        return category;
    }
}
