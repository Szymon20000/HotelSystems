package models;

import play.data.validation.Constraints;

import java.util.Date;

public class Orders {
    @Constraints.Required
    public int Id;
    @Constraints.Required
    public int IdGuest;
    public Date ReceivedDate;
    @Constraints.Required
    public int IdOrderCategories;

    @Constraints.Required
    public int IdNotification;

    Orders() {}

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public int getIdGuest() {
        return IdGuest;
    }

    public void setIdGuest(int idGuest) {
        IdGuest = idGuest;
    }

    public Date getReceivedDate() {
        return ReceivedDate;
    }

    public void setReceivedDate(Date receivedDate) {
        ReceivedDate = receivedDate;
    }

    public int getIdOrderCategories() {
        return IdOrderCategories;
    }

    public void setIdOrderCategories(int idOrderCategories) {
        IdOrderCategories = idOrderCategories;
    }

    public int getIdNotification() {
        return IdNotification;
    }

    public void setIdNotification(int idNotification) {
        IdNotification = idNotification;
    }
}
