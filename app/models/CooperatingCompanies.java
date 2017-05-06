package models;

import play.data.validation.Constraints;

public class CooperatingCompanies {
    @Constraints.Required
    public Integer id;
    @Constraints.Required
    @Constraints.MaxLength(300)
    public String name;
    @Constraints.Required
    @Constraints.MaxLength(50)
    public String phone;
    @Constraints.MaxLength(50)
    public String mail;

    public CooperatingCompanies() {}

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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }
}
