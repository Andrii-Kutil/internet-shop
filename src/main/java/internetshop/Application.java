package internetshop;

import internetshop.lib.Injector;
import internetshop.model.Product;
import internetshop.service.ProductService;
import java.math.BigDecimal;

public class Application {
    private static Injector injector = Injector.getInstance("internetshop");

    public static void main(String[] args) {
        ProductService productService = (ProductService) injector.getInstance(ProductService.class);
        Product product1 = new Product("Apple", new BigDecimal(10));
        Product product2 = new Product("Car", new BigDecimal(100000));

        productService.create(product1);
        productService.create(product2);
        System.out.println(productService.getAll());
        productService.update(new Product("Car", new BigDecimal(500000), product2.getId()));
        System.out.println(productService.getAll());
        System.out.println(productService.get(product1.getId()));
        System.out.println(productService.delete(product1.getId()));
        System.out.println(productService.getAll());

    }
}
