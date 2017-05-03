package authorization.models;

import models.Model;

/**
 * Created by szymon on 5/3/17.
 */
public class Session extends Model{

    public Session() {}

    public Session(int id, String sessionId, int userId, String expirationDate) {
        this.id = id;
        this.sessionId = sessionId;
        this.userId = userId;
        this.expirationDate = expirationDate;
    }

    public int id;
    public String sessionId;
    public int userId;
    public String expirationDate;
}
