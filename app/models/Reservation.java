package models;

import play.data.validation.Constraints;

import java.util.Date;

public class Reservation extends Model {
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

    public Reservation() {}

    public Integer getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Integer getIdRoom() {
        return idRoom;
    }

    public void setIdRoom(int idRoom) {
        this.idRoom = idRoom;
    }

    public Integer getIdGuest() {
        return idGuest;
    }

    public void setIdGuest(int idGuest) {
        this.idGuest = idGuest;
    }

    public Date getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(Date dateFrom) {
        this.dateFrom = dateFrom;
    }

    public Date getDateTo() {
        return dateTo;
    }

    public void setDateTo(Date dateTo) {
        this.dateTo = dateTo;
    }

    public Integer getIdNotification() {
        return idNotification;
    }

    public void setIdNotification(int idNotification) {
        this.idNotification = idNotification;
    }
}
