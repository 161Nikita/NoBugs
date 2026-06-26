package practice_11_complex_tasks.inventory_service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class InventoryService {

    private volatile boolean isInventoryOpen = true;

    public void setInventoryOpen(boolean open) {
        this.isInventoryOpen = open;
    }

    private final Map<String, List<Product>> productList = new HashMap<>();

    public synchronized void addProduct(Product product) {
        if (!isInventoryOpen) {
            throw new OutOfStockException("На данный момент добавление товара недоступно");
        }
        if (product == null) {
            throw new OutOfStockException("Товар не может быть null");
        }
        productList.computeIfAbsent(product.getCategory().toLowerCase(), c -> new ArrayList<>()).add(product);
    }

    public synchronized Product getProductByCategory(String category) {
        if (category == null) {
            throw new IllegalArgumentException("Категория не может быть null");
        }
        List<Product> products = productList.get(category.toLowerCase());

        if (products == null || products.isEmpty()) {
            throw new OutOfStockException("Товары в категории \"" + category + "\" отсутствуют");
        }

        return products.remove(0);
    }

    public synchronized List<Product> filterByPrice(double price) {
        return productList.values().stream().flatMap(p -> p.stream())
                .filter(product -> product.getPrice() > price)
                .collect(Collectors.toList());
    }

    public synchronized List<Product> getAllByCategory(String category) {
        if (category == null) {
            throw new IllegalArgumentException("Категория не может быть null");
        }
        return productList.entrySet().stream()
                .filter(entry -> entry.getKey().equalsIgnoreCase(category))
                .flatMap(entry -> entry.getValue().stream())
                .collect(Collectors.toList());
    }
}