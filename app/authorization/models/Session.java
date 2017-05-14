package authorization.models;

import models.Model;
import play.data.validation.Constraints;

import java.math.BigInteger;
import java.security.SecureRandom;

public class Session extends Model {
    @Constraints.Required
    public Integer id;
    @Constraints.Required
    public String sessionId;
    @Constraints.Required
    public Integer userId;
    @Constraints.Required
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
            if(!(new Session().load("sessionId", tempId))) {
                break;
            }
        }
        return tempId;
    }

    String drawId(int len) {
        return new BigInteger(130, new SecureRandom()).toString(32).substring(0, len);
    }

}
