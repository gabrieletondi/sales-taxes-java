package it.gabrieletondi.salestaxes;

public interface CategoryRepository {
    Category ofProduct(String productName);
}
