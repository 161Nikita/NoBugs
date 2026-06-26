package homework_mock.task_oop.cart_item;

public class CartItem {
    private Product product;
    private int quantity;

    public CartItem(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    public void changeQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getTotal() {
        return product.getPrice() * quantity;
    }

    // Дополнительный геттер (нужен для корзины, чтобы проверять имя товара)
    public Product getProduct() {
        return product;
    }

    public int getQuantity() {
        return quantity;
    }
}
