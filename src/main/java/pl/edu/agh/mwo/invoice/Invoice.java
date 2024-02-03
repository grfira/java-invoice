package pl.edu.agh.mwo.invoice;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import pl.edu.agh.mwo.invoice.product.Product;

public class Invoice {
    private Map<Product, Integer> products;

    public Invoice() {
        this.products = new HashMap<>();
    }

    public void addProduct(Product product) {
        addProduct(product, 1);
    }

    public void addProduct(Product product, Integer quantity) {
        if (product == null || quantity <= 0) {
            throw new IllegalArgumentException("Product cannot be null and quantity should be greater than zero!");
        }
        if (!products.containsKey(product)) {
            products.put(product, quantity);
        } else {
            products.put(product, products.get(product) + quantity);
        }
    }

    public BigDecimal getSubtotal() {
        return products
                .entrySet()
                .stream()
                .map(x -> x.getKey().getPrice().multiply(BigDecimal.valueOf(x.getValue())))
                .reduce(BigDecimal.ZERO, (a, b) -> a.add(b));
    }

    public BigDecimal getTax() {
        return products
                .entrySet()
                .stream()
                .map(x -> x.getKey().getTaxPercent()
                        .multiply(x.getKey().getPrice())
                        .multiply(BigDecimal.valueOf(x.getValue())))
                .reduce(BigDecimal.ZERO, (a, b) -> a.add(b));
    }

    public BigDecimal getTotal() {
        return products
                .entrySet()
                .stream()
                .map(x -> x.getKey().getPriceWithTax().multiply(BigDecimal.valueOf(x.getValue())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
