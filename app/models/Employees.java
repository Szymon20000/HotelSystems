package models;

import play.data.validation.Constraints;
public class Employees {
    @Constraints.Required
    public int Id;
    @Constraints.Required
    @Constraints.MaxLength(200)
    public String Name;
    @Constraints.Required
    @Constraints.MaxLength(300)
    public String Address;
    @Constraints.Required
    @Constraints.MaxLength(50)
    public String Phone;
    @Constraints.MaxLength(100)
    public String Mail;
    @Constraints.Required
    public int IdDepartment;
    public double Bonus;

    public Employees() {}

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

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
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

    public int getIdDepartment() {
        return IdDepartment;
    }

    public void setIdDepartment(int idDepartment) {
        IdDepartment = idDepartment;
    }

    public double getBonus() {
        return Bonus;
    }

    public void setBonus(double bonus) {
        Bonus = bonus;
    }
}
