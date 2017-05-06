package models;

import play.data.validation.Constraints;
public class Classes {
    @Constraints.Required
    public Integer id;
    @Constraints.Required
    @Constraints.Max(200)
    public String name;
    @Constraints.Required
    public Double basePrice;

    Classes() {}

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

    public Double getBasePrice() {
        return basePrice;
    }

    public void setBasePrice(Double basePrice) {
        this.basePrice = basePrice;
    }
}
