package authorization.models;

public class UserForm extends User {
    public String pass;

    @Override
    public void save() throws NoSuchFieldException, IllegalAccessException {
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
