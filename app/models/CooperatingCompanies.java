package models;
import play.data.validation.Constraints;
public class CooperatingCompanies {
    @Constraints.Required
    public int Id;
    @Constraints.Required
    @Constraints.MaxLength(300)
    public String Name;
    @Constraints.Required
    //@Constraints.MaxLength(50)
    public String Phone;
    @Constraints.MaxLength(50)
    public String Mail;

    public CooperatingCompanies() {}


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

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public String getMail() {
        return Mail;
    }

    public void setMail(String mail) {
        Mail = mail;
    }
}
