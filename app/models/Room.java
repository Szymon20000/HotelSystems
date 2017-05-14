package models;

import play.data.validation.Constraints;

import java.math.BigDecimal;

public class Room extends Model {
    @Constraints.Required
    public Integer id;
    @Constraints.Required
    public BigDecimal price;
    @Constraints.Required
    public Integer numberOfBeds;
    @Constraints.Required
    public Integer idStandard;
    @Constraints.Required
    public Integer floor;
    public Integer idPhoto;

    public Room() {}

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

    public Integer getNumberOfBeds() {
        return numberOfBeds;
    }

    public void setNumberOfBeds(Integer numberOfBeds) {
        this.numberOfBeds = numberOfBeds;
    }

    public Integer getIdStandard() {
        return idStandard;
    }

    public void setIdStandard(Integer idStandard) {
        this.idStandard = idStandard;
    }

    public Integer getFloor() {
        return floor;
    }

    public void setFloor(Integer floor) {
        this.floor = floor;
    }

    public Integer getIdPhoto() {
        return idPhoto;
    }

    public void setIdPhoto(Integer idPhoto) {
        this.idPhoto = idPhoto;
    }

}
