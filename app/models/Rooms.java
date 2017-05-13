package models;

import play.data.validation.Constraints;

import java.math.BigDecimal;

public class Rooms extends Model {
    @Constraints.Required
    public Integer id;
    @Constraints.Required
    public BigDecimal price;
    @Constraints.Required
    public Integer nuberOfBeds;
    @Constraints.Required
    public Integer idClass;
    @Constraints.Required
    public Integer floor;

    public Rooms() {}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getNuberOfBeds() {
        return nuberOfBeds;
    }

    public void setNuberOfBeds(Integer nuberOfBeds) {
        this.nuberOfBeds = nuberOfBeds;
    }

    public Integer getIdClass() {
        return idClass;
    }

    public void setIdClass(Integer idClass) {
        this.idClass = idClass;
    }

    public Integer getFloor() {
        return floor;
    }

    public void setFloor(Integer floor) {
        this.floor = floor;
    }
}
