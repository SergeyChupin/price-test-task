package ru.csi.model;

import ru.csi.util.IdGenerator;

import java.math.BigInteger;
import java.time.Instant;

public class Price {

    private int id;
    private PriceInfo extraInfo;
    private Instant beginDate;
    private Instant endDate;
    private BigInteger value;

    public Price(
            int id,
            PriceLevel number,
            Department department,
            Instant beginDate,
            Instant endDate,
            BigInteger value) {
        this.id = id;
        this.extraInfo = new PriceInfo(number, department);
        this.beginDate = beginDate;
        this.endDate = endDate;
        this.value = value;
    }

    public Price copy() {
        return new Price(
                IdGenerator.generateId(),
                extraInfo.getPriceLevel(),
                extraInfo.getDepartment(),
                beginDate,
                endDate,
                value
        );
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public PriceInfo getExtraInfo() {
        return extraInfo;
    }

    public void setExtraInfo(PriceInfo extraInfo) {
        this.extraInfo = extraInfo;
    }

    public Instant getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(Instant beginDate) {
        this.beginDate = beginDate;
    }

    public Instant getEndDate() {
        return endDate;
    }

    public void setEndDate(Instant endDate) {
        this.endDate = endDate;
    }

    public BigInteger getValue() {
        return value;
    }

    public void setValue(BigInteger value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Price price = (Price) o;

        if (id != price.id) return false;
        if (extraInfo != null ? !extraInfo.equals(price.extraInfo) : price.extraInfo != null) return false;
        if (beginDate != null ? !beginDate.equals(price.beginDate) : price.beginDate != null) return false;
        if (endDate != null ? !endDate.equals(price.endDate) : price.endDate != null) return false;
        return value != null ? value.equals(price.value) : price.value == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (extraInfo != null ? extraInfo.hashCode() : 0);
        result = 31 * result + (beginDate != null ? beginDate.hashCode() : 0);
        result = 31 * result + (endDate != null ? endDate.hashCode() : 0);
        result = 31 * result + (value != null ? value.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Price [id=" + id +
                ", extraInfo=" + extraInfo +
                ", beginDate=" + beginDate +
                ", endDate=" + endDate +
                ", value=" + value +
                ']';
    }
}
