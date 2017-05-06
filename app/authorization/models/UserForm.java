package authorization.models;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class UserForm extends User {
    public String pass;

    @Override
    public void save() throws NoSuchFieldException, IllegalAccessException {
        throw new NotImplementedException();
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getPass() {
        return this.pass;
    }
}
