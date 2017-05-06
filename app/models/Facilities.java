package models;

import play.data.validation.Constraints;
public class Facilities {
    @Constraints.Required
    public int Id;
    @Constraints.Required
    @Constraints.MaxLength(200)
    public String Name;
    public String Description;
    @Constraints.Required
    public int ResponsibleWorker;

    public Facilities() {}

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

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public int getResponsibleWorker() {
        return ResponsibleWorker;
    }

    public void setResponsibleWorker(int responsibleWorker) {
        ResponsibleWorker = responsibleWorker;
    }
}
