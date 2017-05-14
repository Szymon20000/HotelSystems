package models;

import play.data.validation.Constraints;

public class NotificationType {
    @Constraints.Required
    public Integer id;
    @Constraints.Required
    @Constraints.MaxLength(200)
    public String name;

    public NotificationType() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        name = name;
    }
}
