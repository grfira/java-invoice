package pl.edu.agh.mwo.invoice.product;

import java.math.BigDecimal;

public class FuelCanisterProduct extends ExciseProduct {
    public FuelCanisterProduct(String name, BigDecimal price) {
        super(name, price, new BigDecimal("0.0"), true);
    }
}
