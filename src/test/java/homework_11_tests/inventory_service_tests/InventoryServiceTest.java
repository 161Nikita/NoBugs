package homework_11_tests.inventory_service_tests;

import homework_11.inventory_service.InventoryService;
import homework_11.inventory_service.OutOfStockException;
import homework_11.inventory_service.Product;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class InventoryServiceTest {
    /**
     * Позитивные кейсы:
     * <p>
     * Добавления товара 0 -> 1
     * Извлечение товара 1 -> 0
     * Фильтрация по цене >500 -> 2 товара нашлось
     * Получение товара по категории 2 -> 2 товара нашлось
     * <p>
     * Угловые кейсы:
     * Проверка флага isInventoryOpen -> false
     * Проверка фильтрации 500, 501 -> 501
     * <p>
     * Негативные кейсы:
     * <p>
     * Товар по категории отсутствует -> OutOfStockException
     * Фильтр по отрицательной цене -> OutOfStockException
     */


    InventoryService service = new InventoryService();

    public void isInventoryOpen() {
        service.inventoryOpen(true);
    }

    @Test
    @DisplayName("Добавления товара 0 -> 1")
    public void testAddProduct() {
        service.inventoryOpen(true);
        service.addProduct(new Product("Мегабук", 10000, "Ноутбуки"));

        List<Product> list = service.getAllByCategory("Ноутбуки");

        assertEquals(1, list.size());
    }

    @Test
    @DisplayName("Извлечение товара 1 -> 0")
    public void testGetProduct() {
        service.inventoryOpen(true);
        service.addProduct(new Product("Мегабук", 10000, "Ноутбуки"));

        List<Product> list1 = service.getAllByCategory("Ноутбуки");

        assertEquals(1, list1.size());

        Product removeProduct = service.getProductByCategory("Ноутбуки");

        assertEquals("Мегабук", removeProduct.getName());

        List<Product> list2 = service.getAllByCategory("Ноутбуки");

        assertEquals(0, list2.size());

    }

    @Test
    @DisplayName("Фильтрация по цене >500 -> 2 товара нашлось")
    public void testFilterByPrice() {
        service.inventoryOpen(true);

        service.addProduct(new Product("Мегабук", 10000, "Ноутбуки"));
        service.addProduct(new Product("Мегабук2", 501, "Ноутбуки"));
        service.addProduct(new Product("Мегабук3", 500, "Ноутбуки"));

        List<Product> list1 = service.getAllByCategory("Ноутбуки");

        assertEquals(3, list1.size());

        List<Product> list2 = service.filterByPrice(500);

        assertEquals(2, list2.size());

        List<Product> list3 = service.getAllByCategory("Ноутбуки");

        assertEquals(3, list3.size());

    }

    @Test
    @DisplayName("Получение товара по категории 2 -> 2 товара нашлось")
    public void testFilterByCategory() {
        service.inventoryOpen(true);

        service.addProduct(new Product("Мегабук", 10000, "Ноутбуки"));
        service.addProduct(new Product("Мегабук2", 501, "Ноутбуки"));
        service.addProduct(new Product("Айфон", 500, "Телефоны"));

        List<Product> list1 = service.getAllByCategory("Ноутбуки");

        assertEquals(2, list1.size());
    }

    @Test
    @DisplayName("Проверка флага isInventoryOpen -> false")
    public void testsInventoryOpenFalse() {
        service.inventoryOpen(false);

        Product product = new Product("Iphone", 20000, "Phone");


        assertThrows(OutOfStockException.class, () -> {
            service.addProduct(product);
        });
    }

    @Test
    @DisplayName("Товар по категории отсутствует -> OutOfStockException")
    public void testsNotFoundCategory() {
        service.inventoryOpen(true);

        Product product = new Product("Iphone", 20000, "Phone");

        service.addProduct(product);

        assertThrows(OutOfStockException.class, () -> {
            service.getProductByCategory("Ноутбуки");
        });
    }

    @Test
    @DisplayName("Фильтр по отрицательной цене -> OutOfStockException")
    public void testNegativePrice() {
        service.inventoryOpen(true);

        Product product = new Product("Iphone", 20000, "Phone");

        service.addProduct(product);

        assertThrows(OutOfStockException.class, () -> {
            service.filterByPrice(-2000);
        });
    }
}
