package models;

import play.data.validation.Constraints;
public class Classes {
    @Constraints.Required
    public int Id;
    @Constraints.Required
    @Constraints.Max(200)
    public String Name;
    @Constraints.Required
    public double BasePrice;

    Classes() {}

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public double getBasePrice() {
        return BasePrice;
    }

    public void setBasePrice(double basePrice) {
        BasePrice = basePrice;
    }
}
