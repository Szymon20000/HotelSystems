package models;

import play.data.validation.Constraints;

public class Photo extends Model {
    @Constraints.Required
    public Integer id;
    @Constraints.Required
    public Integer idFacility;
    @Constraints.Required
    @Constraints.MaxLength(200)
    public String filepath;

    public Photo() {}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIdFacility() {
        return idFacility;
    }

    public void setIdFacility(Integer idFacility) {
        this.idFacility = idFacility;
    }

    public String getFilepath() {
        return filepath;
    }

    public void setFilepath(String filepath) {
        this.filepath = filepath;
    }
}
