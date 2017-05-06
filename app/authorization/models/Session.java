package authorization.models;

import models.Model;

public class Session extends Model{

    public Session() {}

    public Session(int id, String sessionId, int userId, long expirationDate) {
        this.id = id;
        this.sessionId = sessionId;
        this.userId = userId;
        this.expirationDate = expirationDate;
    }

    public int id;
    public String sessionId;
    public int userId;
    public long expirationDate;

    public String getNewId(int len) throws NoSuchFieldException, IllegalAccessException {
        String tempId;
        while(true) {
            tempId = drawId(len);
            if(!(new Session().find("sessionId", id))) {
                break;
            }
        }
        return tempId;
    }

    String drawId(int dl) {
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < dl; ++i) {
            sb.append( sb.append('a' + (int)(Math.random() * ((int)'Z'))));
        }
        return sb.toString();
    }

}
