package models;

import play.data.validation.Constraints;

import javax.validation.Constraint;
import java.util.Date;

public class Notifications {
    @Constraints.Required
    public int Id;
    public int IdType;
    public int IdDepartment;
    public int Priority;
    public Date ReceivedTime;
    @Constraints.Required
    public Boolean Done;


    public Notifications() {}

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public int getIdType() {
        return IdType;
    }

    public void setIdType(int idType) {
        IdType = idType;
    }

    public int getIdDepartment() {
        return IdDepartment;
    }

    public void setIdDepartment(int idDepartment) {
        IdDepartment = idDepartment;
    }

    public int getPriority() {
        return Priority;
    }

    public void setPriority(int priority) {
        Priority = priority;
    }

    public Date getReceivedTime() {
        return ReceivedTime;
    }

    public void setReceivedTime(Date receivedTime) {
        ReceivedTime = receivedTime;
    }

    public Boolean getDone() {
        return Done;
    }

    public void setDone(Boolean done) {
        Done = done;
    }
}
