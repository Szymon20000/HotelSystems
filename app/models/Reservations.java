package models;

import play.data.validation.Constraints;

import java.util.Date;

public class Reservations {
    @Constraints.Required
    public Integer id;
    @Constraints.Required
    public Integer idRoom;
    @Constraints.Required
    public Integer idGuest;
    @Constraints.Required
    public Date dateFrom;
    @Constraints.Required
    public Date dateTo;
    @Constraints.Required
    public Integer idNotification;

    public Reservations() {}

    public Integer getId() {
        return id;
    }

    public void setId(int id) {
        id = id;
    }

    public Integer getIdRoom() {
        return idRoom;
    }

    public void setIdRoom(int idRoom) {
        idRoom = idRoom;
    }

    public Integer getIdGuest() {
        return idGuest;
    }

    public void setIdGuest(int idGuest) {
        idGuest = idGuest;
    }

    public Date getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(Date dateFrom) {
        dateFrom = dateFrom;
    }

    public Date getDateTo() {
        return dateTo;
    }

    public void setDateTo(Date dateTo) {
        dateTo = dateTo;
    }

    public Integer getIdNotification() {
        return idNotification;
    }

    public void setIdNotification(int idNotification) {
        idNotification = idNotification;
    }
}
