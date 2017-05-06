package models;

import play.data.validation.Constraints;

public class Rooms {
    @Constraints.Required
    public int Id;
    @Constraints.Required
    public double Price;
    @Constraints.Required
    public int NuberOfBeds;
    @Constraints.Required
    public int IdClass;
    @Constraints.Required
    public int Floor;

    Rooms() {}

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public double getPrice() {
        return Price;
    }

    public void setPrice(double price) {
        Price = price;
    }

    public int getNuberOfBeds() {
        return NuberOfBeds;
    }

    public void setNuberOfBeds(int nuberOfBeds) {
        NuberOfBeds = nuberOfBeds;
    }

    public int getIdClass() {
        return IdClass;
    }

    public void setIdClass(int idClass) {
        IdClass = idClass;
    }

    public int getFloor() {
        return Floor;
    }

    public void setFloor(int floor) {
        Floor = floor;
    }
}
