package models;

import play.data.validation.Constraints;

import java.util.Date;

public class Order {
    @Constraints.Required
    public Integer id;
    @Constraints.Required
    public Integer idGuest;
    public Date receivedDate;
    @Constraints.Required
    public Integer idOrderCategory;
    @Constraints.Required
    public Integer idNotification;

    public Order() {}

    public Integer getId() {
        return id;
    }

    public void setId(int id) {
        id = id;
    }

    public Integer getIdGuest() {
        return idGuest;
    }

    public void setIdGuest(int idGuest) {
        idGuest = idGuest;
    }

    public Date getReceivedDate() {
        return receivedDate;
    }

    public void setReceivedDate(Date receivedDate) {
        receivedDate = receivedDate;
    }

    public Integer getIdOrderCategory() {
        return idOrderCategory;
    }

    public void setIdOrderCategory(int idOrderCategory) {
        idOrderCategory = idOrderCategory;
    }

    public Integer getIdNotification() {
        return idNotification;
    }

    public void setIdNotification(int idNotification) {
        idNotification = idNotification;
    }
}
