package ru.csi.model;

public class PriceInfo {

    private PriceLevel priceLevel;
    private Department department;

    public PriceInfo(
            PriceLevel priceLevel,
            Department department) {
        this.priceLevel = priceLevel;
        this.department = department;
    }

    public PriceLevel getPriceLevel() {
        return priceLevel;
    }

    public void setPriceLevel(PriceLevel priceLevel) {
        this.priceLevel = priceLevel;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PriceInfo priceInfo = (PriceInfo) o;

        if (priceLevel != priceInfo.priceLevel) return false;
        return department == priceInfo.department;
    }

    @Override
    public int hashCode() {
        int result = priceLevel != null ? priceLevel.hashCode() : 0;
        result = 31 * result + (department != null ? department.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "PriceInfo [priceLevel=" + priceLevel +
                ", department=" + department +
                ']';
    }
}
