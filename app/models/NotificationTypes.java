package models;

import play.data.validation.Constraints;

public class NotificationTypes {
    @Constraints.Required
    public int Id;
    @Constraints.Required
    @Constraints.MaxLength(200)
    public String Name;

    NotificationTypes() {}

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }
}
