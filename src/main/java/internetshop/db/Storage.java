package internetshop.db;

import internetshop.model.Product;
import java.util.ArrayList;
import java.util.List;

public class Storage {
    public static final List<Product> products = new ArrayList<>();
    private static Long unique_id = 0L;

    public static void addProduct(Product product) {
        product.setId(unique_id++);
        products.add(product);
    }
}
