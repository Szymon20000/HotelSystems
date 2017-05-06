package models;

import play.data.validation.Constraints;

import java.util.Date;

public class Orders {
    @Constraints.Required
    public Integer id;
    @Constraints.Required
    public Integer idGuest;
    public Date receivedDate;
    @Constraints.Required
    public Integer idOrderCategories;

    @Constraints.Required
    public Integer idNotification;

    public Orders() {}

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

    public Integer getIdOrderCategories() {
        return idOrderCategories;
    }

    public void setIdOrderCategories(int idOrderCategories) {
        idOrderCategories = idOrderCategories;
    }

    public Integer getIdNotification() {
        return idNotification;
    }

    public void setIdNotification(int idNotification) {
        idNotification = idNotification;
    }
}
