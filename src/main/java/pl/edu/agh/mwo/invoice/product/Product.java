package pl.edu.agh.mwo.invoice.product;

import java.math.BigDecimal;

public abstract class Product {
    private final String name;

    private final BigDecimal price;

    private final BigDecimal taxPercent;

    protected Product(String name, BigDecimal price, BigDecimal tax) {
        if (price == null) {
            throw new IllegalArgumentException("Price cannot be NULL!");
        }
        if (price.signum() < 0) {
            throw new IllegalArgumentException("Price cannot be NEGATIVE!");
        }
        if (name == null) {
            throw new IllegalArgumentException("Name cannot be NULL!");
        }
        if (name.equals("")) {
            throw new IllegalArgumentException("Name cannot be empty!");
        }
        this.name = name;
        this.price = price;
        this.taxPercent = tax;
    }

    public String getName() {
        return this.name;
    }

    public BigDecimal getPrice() {
        return this.price;
    }

    public BigDecimal getTaxPercent() {
        return this.taxPercent;
    }

    public BigDecimal getPriceWithTax() {
        return price.add(price.multiply(taxPercent));
    }
}
