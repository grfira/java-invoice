package pl.edu.agh.mwo.invoice.product;

import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;

public class ProductTest {
    @Test
    public void testProductNameIsCorrect() {
        Product product = new OtherProduct("buty", new BigDecimal("100.0"));
        Assert.assertEquals("buty", product.getName());
    }

    @Test
    public void testProductPriceAndTaxWithDefaultTax() {
        Product product = new OtherProduct("Ogorki", new BigDecimal("100.0"));
        Assert.assertThat(new BigDecimal("100"), Matchers.comparesEqualTo(product.getPrice()));
        Assert.assertThat(new BigDecimal("0.23"), Matchers.comparesEqualTo(product.getTaxPercent()));
    }

    @Test
    public void testProductPriceAndTaxWithDairyProduct() {
        Product product = new DairyProduct("Szarlotka", new BigDecimal("100.0"));
        Assert.assertThat(new BigDecimal("100"), Matchers.comparesEqualTo(product.getPrice()));
        Assert.assertThat(new BigDecimal("0.08"), Matchers.comparesEqualTo(product.getTaxPercent()));
    }

    @Test
    public void testPriceWithTax() {
        Product product = new DairyProduct("Oscypek", new BigDecimal("100.0"));
        Assert.assertThat(new BigDecimal("108"), Matchers.comparesEqualTo(product.getPriceWithTax()));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testProductWithNullName() {
        new OtherProduct(null, new BigDecimal("100.0"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testProductWithEmptyName() {
        new TaxFreeProduct("", new BigDecimal("100.0"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testProductWithNullPrice() {
        new DairyProduct("Banany", null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testProductWithNegativePrice() {
        new TaxFreeProduct("Mandarynki", new BigDecimal("-1.00"));
    }

    @Test
    public void testProductWithExcise() {
        Product prod_wine = new BottleOfWineProduct("Komandos STRONG", new BigDecimal(30));
        Assert.assertThat(new BigDecimal("42.46"), Matchers.comparesEqualTo(prod_wine.getPriceWithTax()));
        Assert.assertThat(new BigDecimal("0.23"), Matchers.comparesEqualTo(prod_wine.getTaxPercent()));
    }

    @Test
    public void testProductWithExciseAndAbolition() {
        Product prod_fuel = new FuelCanisterProduct("Benzyna 95", new BigDecimal("6.78"));
        Assert.assertThat(new BigDecimal("6.78"), Matchers.comparesEqualTo(prod_fuel.getPriceWithTax()));
        Assert.assertThat(new BigDecimal("0.00"), Matchers.comparesEqualTo(prod_fuel.getTaxPercent()));
    }
}
