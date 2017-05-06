package authorization.models;

import authorization.Authenticator;
import models.Model;
import play.data.validation.Constraints;

public class User extends Model {
    public Integer id;
    @Constraints.Required
    public String email;
    @Constraints.Required
    public String passHash;
    public Boolean isAdmin;

    public User() {}

    public User(Integer id, String email, String pass, Boolean isAdmin) {
        setId(id);
        setEmail(email);
        setPassHash(pass);
        setIsAdmin(isAdmin);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassHash() {
        return passHash;
    }

    public void setPassHash(String passHash) {
        this.passHash = Authenticator.getHash(passHash);
    }

    public Boolean getIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(Boolean isAdmin) {
        this.isAdmin = isAdmin;
    }
}

