package models;

import play.data.validation.Constraints;

import java.math.BigDecimal;

public class Standard extends Model {
    @Constraints.Required
    public Integer id;
    @Constraints.Required
    @Constraints.Max(200)
    public String name;
    @Constraints.Required
    public BigDecimal basePrice;

    public Standard() {}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getBasePrice() {
        return basePrice;
    }

    public void setBasePrice(BigDecimal basePrice) {
        this.basePrice = basePrice;
    }
}
