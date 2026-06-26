package homework_11.inventory_service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class InventoryService {

    private final Map<String, List<Product>> productList = new HashMap<>();

    volatile boolean isInventoryOpen = true;

    public void inventoryOpen(boolean open) {
        this.isInventoryOpen = open;
    }

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
            throw new OutOfStockException("Категория не может быть null");
        }
        List<Product> products = productList.get(category.toLowerCase());
        if (products == null || products.isEmpty()) {
            throw new OutOfStockException("В этой категории товар отсутствует");
        }
        return products.remove(0);
    }

    public synchronized List<Product> filterByPrice(double price) {
        if (price < 0) {
            throw new OutOfStockException("Цена не может быть отрицательной");
        }
        return productList.values().stream().flatMap(p -> p.stream())
                .filter(p -> p.getPrice() > price).collect(Collectors.toList());
    }

    public synchronized List<Product> getAllByCategory(String category) {
        if (category == null) {
            throw new OutOfStockException("Категория не может быть null");
        }
        return productList.entrySet().stream()
                .filter(e -> e.getKey().equalsIgnoreCase(category))
                .flatMap(e -> e.getValue().stream())
                .collect(Collectors.toList());
    }
}