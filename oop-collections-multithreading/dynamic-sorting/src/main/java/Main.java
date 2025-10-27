import model.Product;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Product> products = new java.util.ArrayList<>(List.of(
                new Product("product2", 200, 3),
                new Product("product3", 100, 1),
                new Product("product1", 300, 7)
        ));

        Collections.sort(products);
        System.out.printf("Список продуктов отсортирован по цене: %s%n", products);

        products.sort(Comparator.comparing(Product::getName));
        System.out.printf("Список продуктов отсортирован по названию: %s%n", products);

        products.sort(Comparator.comparing(Product::getWeight).reversed());
        System.out.printf("Список продуктов отсортирован по весу от большего к меньшему: %s%n", products);
    }
}
