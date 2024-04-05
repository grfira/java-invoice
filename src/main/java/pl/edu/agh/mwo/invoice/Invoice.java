package pl.edu.agh.mwo.invoice;

import pl.edu.agh.mwo.invoice.product.Product;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class Invoice {
    private Map<Product, Integer> products = new HashMap<Product, Integer>();
    private static int lastNumber = 0;
    private int number;

    public void addProduct(Product product) {
        addProduct(product, 1);
    }

    public void addProduct(Product product, Integer quantity) {
        if (product == null || quantity <= 0) {
            throw new IllegalArgumentException();
        }
        products.put(product, quantity);
    }

    public BigDecimal getNetTotal() {
        BigDecimal totalNet = BigDecimal.ZERO;
        for (Product product : products.keySet()) {
            BigDecimal quantity = new BigDecimal(products.get(product));
            totalNet = totalNet.add(product.getPrice().multiply(quantity));
        }
        return totalNet;
    }

    public BigDecimal getTaxTotal() {
        return getGrossTotal().subtract(getNetTotal());
    }

    public BigDecimal getGrossTotal() {
        BigDecimal totalGross = BigDecimal.ZERO;
        for (Product product : products.keySet()) {
            BigDecimal quantity = new BigDecimal(products.get(product));
            totalGross = totalGross.add(product.getPriceWithTax().multiply(quantity));
        }
        return totalGross;
    }

    public int getNumber() {
        return this.number;
    }

    public Invoice() {
        this.number = ++lastNumber;
    }

    public String getInvoiceToPrint() {
        NumberFormat format = NumberFormat.getCurrencyInstance(Locale.getDefault());
        StringBuilder str = new StringBuilder("Invoice Number: ");
        str.append(this.getNumber()).append("\n");
        products.forEach((product, quantity) -> str.append(product.getName())
                .append("\t")
                .append(quantity)
                .append("\t")
                .append(format.format(product.getPriceWithTax().multiply(new BigDecimal(quantity))))
                .append("\n"));
        int totalNumber = products.values().stream().reduce(0, (x, y) -> x + y);
        str.append("Total number of items: ").append(totalNumber);

        return str.toString();
    }
}
