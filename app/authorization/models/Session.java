package authorization.models;

import models.Model;

import java.math.BigInteger;
import java.security.SecureRandom;

public class Session extends Model {
    public Integer id;
    public String sessionId;
    public Integer userId;
    public Long expirationDate;

    public Session() {}

    public Session(Integer id, String sessionId, Integer userId, Long expirationDate) {
        this.id = id;
        this.sessionId = sessionId;
        this.userId = userId;
        this.expirationDate = expirationDate;
    }

    public String getNewId(int len) throws NoSuchFieldException, IllegalAccessException {
        String tempId;
        while(true) {
            tempId = drawId(len);
            if(!(new Session().load("sessionId", id))) {
                break;
            }
        }
        return tempId;
    }

    String drawId(int len) {
        return new BigInteger(130, new SecureRandom()).toString(32).substring(0, len);
    }

}
