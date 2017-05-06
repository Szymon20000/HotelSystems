package models;

import play.data.validation.Constraints;

public class Photos {
    @Constraints.Required
    public int Id;
    @Constraints.Required
    public int IdFacility;
    @Constraints.Required
    @Constraints.MaxLength(200)
    public String Filepath;

    public Photos() {}

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public int getIdFacility() {
        return IdFacility;
    }

    public void setIdFacility(int idFacility) {
        IdFacility = idFacility;
    }

    public String getFilepath() {
        return Filepath;
    }

    public void setFilepath(String filepath) {
        Filepath = filepath;
    }
}
