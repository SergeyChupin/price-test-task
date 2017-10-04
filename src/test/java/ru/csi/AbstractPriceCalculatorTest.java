package ru.csi;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import ru.csi.BasePriceCalculator;
import ru.csi.ICalculator;
import ru.csi.model.Department;
import ru.csi.model.Price;
import ru.csi.model.PriceLevel;
import ru.csi.model.Product;

import java.math.BigInteger;
import java.time.Instant;
import java.util.List;
import java.util.Objects;

public abstract class AbstractPriceCalculatorTest {

    protected static ICalculator<List<Product>> calculator;
    protected List<Product> result;

    static {
        calculator = new BasePriceCalculator();
    }

    protected abstract List<Product> getProducts();

    @Before
    public void setUp() {
        System.out.println("Initial data");
        print(getProducts());
    }

    @After
    public void tearDown() {
        System.out.println("Result");
        print(result);
    }

    private void print(List<Product> list) {
        for (Product item : list) {
            System.out.println("\tCode: " + item.getCode());
            for (Price price : item.getPrices()) {
                System.out.println("\t\t" + price);
            }
        }
    }

    protected void calculate(List<Product> newProductPrices) {
        result = calculator.calculate(getProducts(), newProductPrices);
    }

    protected void checkPrice(
            Product product,
            PriceLevel level,
            Department department,
            Instant beginDate,
            Instant endDate,
            BigInteger value) {
        boolean onlyOne = product.getPrices().stream()
                .filter(price -> Objects.equals(price.getExtraInfo().getPriceLevel(), level) &&
                        Objects.equals(price.getExtraInfo().getDepartment(), department) &&
                        Objects.equals(price.getBeginDate(), beginDate) &&
                        Objects.equals(price.getEndDate(), endDate) &&
                        Objects.equals(price.getValue(), value))
                .count() == 1;
        Assert.assertTrue("Cannot be the same prices", onlyOne);
    }

    protected Product findProduct(int id) {
        for (Product product : result) {
            if (product.getCode() == id) {
                return product;
            }
        }
        Assert.fail("Cannot find product by " + id + " among results");
        return null;
    }
}
