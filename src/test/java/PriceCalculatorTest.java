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

public class PriceCalculatorTest extends AbstractPriceCalculatorTest {

    private static List<Product> products;

    @BeforeClass
    public static void setUpBeforeClass() {
        products = new ArrayList<>();
        products.add(
                new Product(
                        122856,
                        Arrays.asList(
                                new Price(
                                        1,
                                        PriceLevel.ONE,
                                        Department.DEPARTMENT_1,
                                        date(2013, 1, 1),
                                        date(2013, 1, 31, 23, 59, 59),
                                        BigInteger.valueOf(11000)),
                                new Price(
                                        2,
                                        PriceLevel.TWO,
                                        Department.DEPARTMENT_1,
                                        date(2013, 1, 10),
                                        date(2013, 1, 20, 23, 59, 59),
                                        BigInteger.valueOf(99000))
                        )
                )
        );
        products.add(
                new Product(
                       6654,
                        Arrays.asList(
                                new Price(
                                        3,
                                        PriceLevel.ONE,
                                        Department.DEPARTMENT_2,
                                        date(2013, 1, 1),
                                        date(2013, 1, 31),
                                        BigInteger.valueOf(5000))
                        )
                )
        );
    }

    @Test
    public void test() {
        List<Product> newProductPrices = new ArrayList<>();
        newProductPrices.add(
                new Product(
                        122856,
                        Arrays.asList(
                                new Price(
                                        4,
                                        PriceLevel.ONE,
                                        Department.DEPARTMENT_1,
                                        date(2013, 1, 20),
                                        date(2013, 2, 20, 23, 59, 59),
                                        BigInteger.valueOf(11000)),
                                new Price(
                                        5,
                                        PriceLevel.TWO,
                                        Department.DEPARTMENT_1,
                                        date(2013, 1, 15),
                                        date(2013, 1, 25, 23, 59, 59),
                                        BigInteger.valueOf(92000))
                        )
                )
        );
        newProductPrices.add(
                new Product(
                        6654,
                        Arrays.asList(
                                new Price(
                                        6,
                                        PriceLevel.ONE,
                                        Department.DEPARTMENT_2,
                                        date(2013, 1, 12),
                                        date(2013, 1, 13),
                                        BigInteger.valueOf(4000))
                        )
                )
        );

        calculate(newProductPrices);

        Product product = findProduct(122856);
        Assert.assertNotNull(product.getPrices());
        Assert.assertEquals(3, product.getPrices().size());
        checkPrice(product, PriceLevel.ONE, Department.DEPARTMENT_1,
                date(2013, 1, 1),
                date(2013, 2, 20, 23, 59, 59),
                BigInteger.valueOf(11000)
        );
        checkPrice(product, PriceLevel.TWO, Department.DEPARTMENT_1,
                date(2013, 1, 10),
                date(2013, 1, 15),
                BigInteger.valueOf(99000)
        );
        checkPrice(product, PriceLevel.TWO, Department.DEPARTMENT_1,
                date(2013, 1, 15),
                date(2013, 1, 25, 23, 59, 59),
                BigInteger.valueOf(92000)
        );

        product = findProduct(6654);
        Assert.assertNotNull(product.getPrices());
        Assert.assertEquals(3, product.getPrices().size());
        checkPrice(product, PriceLevel.ONE, Department.DEPARTMENT_2,
                date(2013, 1, 1),
                date(2013, 1, 12),
                BigInteger.valueOf(5000)
        );
        checkPrice(product, PriceLevel.ONE, Department.DEPARTMENT_2,
                date(2013, 1, 12),
                date(2013, 1, 13),
                BigInteger.valueOf(4000)
        );
        checkPrice(product, PriceLevel.ONE, Department.DEPARTMENT_2,
                date(2013, 1, 13),
                date(2013, 1, 31),
                BigInteger.valueOf(5000)
        );
    }

    @Override
    protected List<Product> getProducts() {
        return products;
    }
}
