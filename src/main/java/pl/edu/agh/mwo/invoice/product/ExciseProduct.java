package pl.edu.agh.mwo.invoice.product;

import java.math.BigDecimal;

class ExciseProduct extends Product {
    private final BigDecimal excise;

    public ExciseProduct(String name, BigDecimal price, BigDecimal excise, boolean isAbolition) {
        super(name, price, new BigDecimal(isAbolition ? "0.00" : "0.23"));
        this.excise = excise;
    }

    @Override
    public BigDecimal getPriceWithTax() {
        return super.getPriceWithTax().add(excise);
    }
}
