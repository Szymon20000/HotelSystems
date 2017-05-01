package controllers;

import play.data.validation.Constraints;

public class FormData {
    @Constraints.Required
    public String name;
    public String password;
    public Boolean remember;

    public FormData() {}

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
}
