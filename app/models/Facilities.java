package models;

import play.data.validation.Constraints;

public class Facilities {
    @Constraints.Required
    public Integer id;
    @Constraints.Required
    @Constraints.MaxLength(200)
    public String name;
    public String description;
    @Constraints.Required
    public Integer responsibleWorker;

    public Facilities() {}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getResponsibleWorker() {
        return responsibleWorker;
    }

    public void setResponsibleWorker(Integer responsibleWorker) {
        this.responsibleWorker = responsibleWorker;
    }
}
