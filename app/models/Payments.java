package models;

import play.data.validation.Constraints;

public class Payments {
    @Constraints.Required
    public Integer id;
    @Constraints.Required
    public Integer idOrder;
    @Constraints.Required
    public Double amount;
    public String description;
    @Constraints.Required
    public Boolean cash;

    Payments() {}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIdOrder() {
        return idOrder;
    }

    public void setIdOrder(Integer idOrder) {
        this.idOrder = idOrder;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getCash() {
        return cash;
    }

    public void setCash(Boolean cash) {
        this.cash = cash;
    }
}
