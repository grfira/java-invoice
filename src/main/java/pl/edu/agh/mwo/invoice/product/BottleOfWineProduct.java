package pl.edu.agh.mwo.invoice.product;

import java.math.BigDecimal;

public class BottleOfWineProduct extends ExciseProduct {
    public BottleOfWineProduct(String name, BigDecimal price) {
        super(name, price, new BigDecimal("5.56"), false);
    }
}
