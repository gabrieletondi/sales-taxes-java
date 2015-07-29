package it.gabrieletondi.salestaxes.catalog.repository;

import it.gabrieletondi.salestaxes.catalog.Category;

public interface CategoryRepository {
    Category ofProduct(String productName);
}
