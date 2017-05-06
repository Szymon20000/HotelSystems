package models;

import play.data.validation.Constraints;

import java.util.Date;

public class Reservations {
    @Constraints.Required
    public int Id;
    @Constraints.Required
    public int IdRoom;
    @Constraints.Required
    public int IdGuest;
    @Constraints.Required
    public Date DateFrom;
    @Constraints.Required
    public Date DateTo;
    @Constraints.Required
    public int IdNotification;

    Reservations() {}

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public int getIdRoom() {
        return IdRoom;
    }

    public void setIdRoom(int idRoom) {
        IdRoom = idRoom;
    }

    public int getIdGuest() {
        return IdGuest;
    }

    public void setIdGuest(int idGuest) {
        IdGuest = idGuest;
    }

    public Date getDateFrom() {
        return DateFrom;
    }

    public void setDateFrom(Date dateFrom) {
        DateFrom = dateFrom;
    }

    public Date getDateTo() {
        return DateTo;
    }

    public void setDateTo(Date dateTo) {
        DateTo = dateTo;
    }

    public int getIdNotification() {
        return IdNotification;
    }

    public void setIdNotification(int idNotification) {
        IdNotification = idNotification;
    }
}
