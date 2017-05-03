package authorization.models;

import models.Model;

/**
 * Created by szymon on 5/3/17.
 */
public class User extends Model {

    public User() {}

    public User(int id, String mail, String passHash, boolean isAdmin) {
        this.id = id;
        this.mail = mail;
        this.passHash = passHash;
        this.isAdmin = isAdmin;
    }

    public int id;
    public String mail;
    public String passHash;
    public boolean isAdmin;
}

