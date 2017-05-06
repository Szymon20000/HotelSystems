package models;

import play.data.validation.Constraints;

public class Payments {
    @Constraints.Required
    public int Id;
    @Constraints.Required
    public int IdOrder;
    @Constraints.Required
    public double Amount;
    public String Description;
    @Constraints.Required
    public Boolean Cash;

    Payments() {}

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public int getIdOrder() {
        return IdOrder;
    }

    public void setIdOrder(int idOrder) {
        IdOrder = idOrder;
    }

    public double getAmount() {
        return Amount;
    }

    public void setAmount(double amount) {
        Amount = amount;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public Boolean getCash() {
        return Cash;
    }

    public void setCash(Boolean cash) {
        Cash = cash;
    }
}
