package ru.csi;

import ru.csi.model.Price;
import ru.csi.model.PriceInfo;

import java.time.Instant;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import static ru.csi.util.TimeUtil.afterAndEquals;
import static ru.csi.util.TimeUtil.beforeAndEquals;

public class BasePriceCalculator extends AbstractPriceCalculator {

    @Override
    protected List<Price> execute(List<Price> prices) {
        HashMap<PriceInfo, List<Price>> temp = new HashMap<>();
        for (Price price : prices) {
            validate(price);
            PriceInfo info = price.getExtraInfo();
            if (temp.containsKey(info)) {
                addNewPrice(temp.get(info), price);
            } else {
                List<Price> list = new ArrayList<>();
                list.add(price);
                temp.put(info, list);
            }
        }
        return temp.values().stream()
                .flatMap(List::stream)
                .collect(Collectors.toList());
    }

    private void validate(Price price) {
        if (price.getEndDate().isBefore(price.getBeginDate())) {
            Instant temp = price.getEndDate();
            price.setEndDate(price.getBeginDate());
            price.setBeginDate(temp);
        }
    }

    private void addNewPrice(List<Price> prices, Price newPrice) {
        Price restPrice = null;
        boolean needAddPrice = true;
        for (Iterator<Price> iterator = prices.iterator(); iterator.hasNext();) {
            Price price = iterator.next();
            if(beforeAndEquals(price.getBeginDate(), newPrice.getEndDate()) &&
                    afterAndEquals(price.getEndDate(), newPrice.getBeginDate())) {
                boolean updatePrice = price.getValue().equals(newPrice.getValue());
                if (!updatePrice && beforeAndEquals(price.getBeginDate(), newPrice.getBeginDate())  &&
                        afterAndEquals(price.getEndDate(), newPrice.getEndDate())) {
                    if (price.getBeginDate().equals(newPrice.getBeginDate())) {
                        price.setBeginDate(newPrice.getEndDate());
                    } else if (price.getEndDate().equals(newPrice.getEndDate())) {
                        price.setEndDate(newPrice.getBeginDate());
                    } else {
                        restPrice = price.copy();
                        restPrice.setBeginDate(newPrice.getEndDate());
                        price.setEndDate(newPrice.getBeginDate());
                    }
                } else if (beforeAndEquals(newPrice.getBeginDate(), price.getBeginDate()) &&
                        afterAndEquals(newPrice.getEndDate(), price.getEndDate())) {
                    if (updatePrice) {
                        price.setBeginDate(newPrice.getBeginDate());
                        price.setEndDate(newPrice.getEndDate());
                        needAddPrice = false;
                    } else {
                        iterator.remove();
                    }
                } else {
                    if (beforeAndEquals(price.getEndDate(), newPrice.getEndDate()) ) {
                        if (updatePrice) {
                            price.setEndDate(newPrice.getEndDate());
                            needAddPrice = false;
                        } else {
                            price.setEndDate(newPrice.getBeginDate());
                        }
                    } else if (afterAndEquals(price.getBeginDate(), newPrice.getBeginDate())) {
                        if (updatePrice) {
                            price.setBeginDate(newPrice.getBeginDate());
                            needAddPrice = false;
                        } else {
                            price.setBeginDate(newPrice.getEndDate());
                        }
                    }
                }
            }
        }
        if (needAddPrice) {
            prices.add(newPrice);
        }
        if (restPrice != null) {
            prices.add(restPrice);
        }
    }
}
