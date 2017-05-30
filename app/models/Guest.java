package models;

import play.data.validation.Constraints;

public class Guest extends Model {

    @Constraints.Required
    public Integer id;
    @Constraints.Required
    @Constraints.MaxLength(200)
    public String name;
    @Constraints.Required
    @Constraints.MaxLength(50)
    public String phone;
    @Constraints.MaxLength(100)
    public String email;
    @Constraints.Required
    public Integer booker;
    public Integer userId;

    public Guest() {}

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getBooker() {
        return booker;
    }

    public void setBooker(Integer booker) {
        this.booker = booker;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
