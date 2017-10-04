package ru.csi;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import ru.csi.model.Department;
import ru.csi.model.Price;
import ru.csi.model.PriceLevel;
import ru.csi.model.Product;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static ru.csi.util.TimeUtil.date;


public class PriceCalculatorTest2 extends AbstractPriceCalculatorTest{

    private static List<Product> products;

    @BeforeClass
    public static void setUpBeforeClass() {
        products = new ArrayList<>();
        products.add(
                new Product(
                        10,
                        Arrays.asList(
                                new Price(
                                        1,
                                        PriceLevel.ONE,
                                        Department.DEPARTMENT_1,
                                        date(2013, 1, 1),
                                        date(2013, 1, 31),
                                        BigInteger.valueOf(50))
                        )
                )
        );
        products.add(
                new Product(
                        11,
                        Arrays.asList(
                                new Price(
                                        3,
                                        PriceLevel.ONE,
                                        Department.DEPARTMENT_1,
                                        date(2013, 1, 1),
                                        date(2013, 1, 5),
                                        BigInteger.valueOf(100)),
                                new Price(
                                        4,
                                        PriceLevel.ONE,
                                        Department.DEPARTMENT_1,
                                        date(2013, 1, 10),
                                        date(2013, 1, 12),
                                        BigInteger.valueOf(120))
                        )
                )
        );
        products.add(
                new Product(
                        12,
                        Arrays.asList(
                                new Price(
                                        5,
                                        PriceLevel.TWO,
                                        Department.DEPARTMENT_1,
                                        date(2013, 1, 1),
                                        date(2013, 1, 10),
                                        BigInteger.valueOf(80)),
                                new Price(
                                        6,
                                        PriceLevel.TWO,
                                        Department.DEPARTMENT_1,
                                        date(2013, 1, 10),
                                        date(2013, 1, 13),
                                        BigInteger.valueOf(87)),
                                new Price(
                                        7,
                                        PriceLevel.TWO,
                                        Department.DEPARTMENT_1,
                                        date(2013, 1, 13),
                                        date(2013, 1, 20),
                                        BigInteger.valueOf(90))
                        )
                )
        );
    }

    @Test
    public void testByExample1() {
        List<Product> newProductPrices = new ArrayList<>();
        newProductPrices.add(
                new Product(
                        10,
                        Arrays.asList(
                                new Price(
                                        2,
                                        PriceLevel.ONE,
                                        Department.DEPARTMENT_1,
                                        date(2013, 1, 10),
                                        date(2013, 1, 20),
                                        BigInteger.valueOf(60))
                        )
                )
        );

        calculate(newProductPrices);

        Product product = findProduct(10);
        Assert.assertNotNull(product.getPrices());
        Assert.assertEquals(3, product.getPrices().size());
        checkPrice(product, PriceLevel.ONE, Department.DEPARTMENT_1,
                date(2013, 1, 1),
                date(2013, 1, 10),
                BigInteger.valueOf(50)
        );
        checkPrice(product, PriceLevel.ONE, Department.DEPARTMENT_1,
                date(2013, 1, 10),
                date(2013, 1, 20),
                BigInteger.valueOf(60)
        );
        checkPrice(product, PriceLevel.ONE, Department.DEPARTMENT_1,
                date(2013, 1, 20),
                date(2013, 1, 31),
                BigInteger.valueOf(50)
        );
    }

    @Test
    public void testByExample2() {
        List<Product> newProductPrices = new ArrayList<>();
        newProductPrices.add(
                new Product(
                        11,
                        Arrays.asList(
                                new Price(
                                        5,
                                        PriceLevel.ONE,
                                        Department.DEPARTMENT_1,
                                        date(2013, 1, 4),
                                        date(2013, 1, 11),
                                        BigInteger.valueOf(110))
                        )
                )
        );

        calculate(newProductPrices);

        Product product = findProduct(11);
        Assert.assertNotNull(product.getPrices());
        Assert.assertEquals(3, product.getPrices().size());
        checkPrice(product, PriceLevel.ONE, Department.DEPARTMENT_1,
                date(2013, 1, 1),
                date(2013, 1, 4),
                BigInteger.valueOf(100)
        );
        checkPrice(product, PriceLevel.ONE, Department.DEPARTMENT_1,
                date(2013, 1, 4),
                date(2013, 1, 11),
                BigInteger.valueOf(110)
        );
        checkPrice(product, PriceLevel.ONE, Department.DEPARTMENT_1,
                date(2013, 1, 11),
                date(2013, 1, 12),
                BigInteger.valueOf(120)
        );
    }

    @Test
    public void testByExample3() {
        List<Product> newProductPrices = new ArrayList<>();
        newProductPrices.add(
                new Product(
                        12,
                        Arrays.asList(
                                new Price(
                                        8,
                                        PriceLevel.TWO,
                                        Department.DEPARTMENT_1,
                                        date(2013, 1, 5),
                                        date(2013, 1, 11),
                                        BigInteger.valueOf(80)),
                                new Price(
                                        9,
                                        PriceLevel.TWO,
                                        Department.DEPARTMENT_1,
                                        date(2013, 1, 11),
                                        date(2013, 1, 18),
                                        BigInteger.valueOf(85))
                        )
                )
        );

        calculate(newProductPrices);

        Product product = findProduct(12);
        Assert.assertNotNull(product.getPrices());
        Assert.assertEquals(3, product.getPrices().size());
        checkPrice(product, PriceLevel.TWO, Department.DEPARTMENT_1,
                date(2013, 1, 1),
                date(2013, 1, 11),
                BigInteger.valueOf(80)
        );
        checkPrice(product, PriceLevel.TWO, Department.DEPARTMENT_1,
                date(2013, 1, 11),
                date(2013, 1, 18),
                BigInteger.valueOf(85)
        );
        checkPrice(product, PriceLevel.TWO, Department.DEPARTMENT_1,
                date(2013, 1, 18),
                date(2013, 1, 20),
                BigInteger.valueOf(90)
        );
    }

    @Override
    protected List<Product> getProducts() {
        return products;
    }
}
