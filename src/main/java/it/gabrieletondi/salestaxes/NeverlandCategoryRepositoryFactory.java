package it.gabrieletondi.salestaxes;

import java.util.HashMap;
import java.util.Map;

public class NeverlandCategoryRepositoryFactory {

    public static CategoryRepository build() {
        Map<String, Category> mapping = new HashMap<String, Category>();
        mapping.put("book", Category.BOOKS);
        mapping.put("music CD", Category.MISC);
        mapping.put("chocolate bar", Category.FOOD);
        mapping.put("box of chocolates", Category.FOOD);
        mapping.put("bottle of perfume", Category.MISC);
        mapping.put("packet of headache pills", Category.MEDICALS);

        return new InMemoryCategoryRepository(mapping);
    }

}
