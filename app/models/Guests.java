package models;

import play.data.validation.Constraints;
public class Guests {
    @Constraints.Required
    public int Id;
    @Constraints.Required
    @Constraints.MaxLength(200)
    public String Name;
    @Constraints.Required
    @Constraints.MaxLength(50)
    public String Phone;
    @Constraints.MaxLength(100)
    public String Mail;
    @Constraints.Required
    public int Booker;

    Guests() {}

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

    public int getBooker() {
        return Booker;
    }

    public void setBooker(int booker) {
        Booker = booker;
    }
}
