package models;

import play.data.validation.Constraints;

public class Photo extends Model {
    @Constraints.Required
    public Integer id;
    @Constraints.Required
    @Constraints.MaxLength(200)
    public String filepath;

    public Photo() {}

    public Photo(Integer id, String filepath) {
        setId(id);
        setFilepath(filepath);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFilepath() {
        return filepath;
    }

    public void setFilepath(String filepath) {
        this.filepath = filepath;
    }
}
