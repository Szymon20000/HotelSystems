package authorization.models;

import play.data.validation.Constraints;

public class UserForm extends User {
    @Constraints.Required
    public String pass;

    @Override
    public void save() {
        throw new UnsupportedOperationException();
    }

    public User convertToUser() {
        return new User(id, email, pass, isAdmin);
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getPass() {
        return this.pass;
    }
}
