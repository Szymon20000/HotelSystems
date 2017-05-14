package models;

import play.data.validation.Constraints;

import java.math.BigDecimal;
import java.util.Date;

public class OrderCategory {
    @Constraints.Required
    public Integer id;
    @Constraints.Required
    @Constraints.MaxLength(200)
    public String name;
    public BigDecimal price;
    public Date orderDate;
    @Constraints.Required
    public Boolean availability;

    public OrderCategory() {}

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

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public Boolean getAvailability() {
        return availability;
    }

    public void setAvailability(Boolean availability) {
        this.availability = availability;
    }
}
