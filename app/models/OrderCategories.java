package models;

import play.data.validation.Constraints;

import java.util.Date;

public class OrderCategories {
    @Constraints.Required
    public int Id;
    @Constraints.Required
    @Constraints.MaxLength(200)
    public String Name;
    public double Price;
    public Date OrderDate;
    @Constraints.Required
    public Boolean Availability;

    OrderCategories() {}

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

    public double getPrice() {
        return Price;
    }

    public void setPrice(double price) {
        Price = price;
    }

    public Date getOrderDate() {
        return OrderDate;
    }

    public void setOrderDate(Date orderDate) {
        OrderDate = orderDate;
    }

    public Boolean getAvailability() {
        return Availability;
    }

    public void setAvailability(Boolean availability) {
        Availability = availability;
    }
}
