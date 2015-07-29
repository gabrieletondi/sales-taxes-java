package it.gabrieletondi.salestaxes.catalog.repository;

import it.gabrieletondi.salestaxes.catalog.Category;

import java.util.HashMap;
import java.util.Map;

import static it.gabrieletondi.salestaxes.catalog.Category.*;

public class NeverlandCategoryRepositoryFactory {

    public static CategoryRepository build() {
        Map<String, Category> mapping = new HashMap<String, Category>();
        mapping.put("book", BOOKS);
        mapping.put("music CD", MISC);
        mapping.put("chocolate bar", FOOD);
        mapping.put("box of chocolates", FOOD);
        mapping.put("bottle of perfume", MISC);
        mapping.put("packet of headache pills", MEDICALS);

        return new InMemoryCategoryRepository(mapping);
    }

}
