package authorization.models;

import authorization.Authenticator;
import authorization.NoSuchUserException;
import play.data.validation.ValidationError;

import java.util.ArrayList;
import java.util.List;

public class UserForm extends User {
    public String pass;

    @Override
    public void save() throws NoSuchFieldException, IllegalAccessException {
        throw new UnsupportedOperationException();
    }

    public List<ValidationError> validate() {
        List<ValidationError> errors = new ArrayList<>();
        try {
            Authenticator.logIn(email, pass);
        }
        catch (NoSuchUserException e) {
            errors.add(new ValidationError("", "Authorization failed"));
        }

        return errors;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getPass() {
        return this.pass;
    }
}
