package ru.csi;

import ru.csi.model.Price;
import ru.csi.model.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public abstract class AbstractPriceCalculator implements ICalculator<List<Product>> {

    @Override
    public List<Product> calculate(List<Product> a, List<Product> b) {
        Map<Integer, List<Product>> collect = Stream.of(a, b)
                .flatMap(List::stream)
                .collect(Collectors.groupingBy(Product::getCode));

        List<Product> result = new ArrayList<>();
        for (Map.Entry<Integer, List<Product>> products : collect.entrySet()) {
            List<Price> prices = products.getValue().stream()
                    .map(Product::getPrices)
                    .collect(ArrayList::new, List::addAll, List::addAll);
            result.add(new Product(products.getKey(), execute(prices)));
        }
        return result;
    }

    protected abstract List<Price> execute(List<Price> prices);
}
