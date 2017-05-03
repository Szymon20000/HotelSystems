package authorization;

import authorization.models.Session;
import authorization.models.User;

import static play.mvc.Controller.session;

/**
 * Created by szymon on 5/3/17.
 */
public class Authenticator {

    public static User getUser() throws NoSuchFieldException, IllegalAccessException {
        if(session().containsKey("sessionId")) {
            String sessionId = session().get("sessionId");
            Session session = new Session();

            if(session.find("sessionId", session.sessionId)) {
                User user = new User();
                user.get(session.userId);
                return user;
            }
        }
        return null;
    }

    public static void logIn(String email, String pass) {
        User user = new User();
        user.mail = email;
        user.passHash = getHash(pass);
        user.auth();
        makeNewSession(user);
    }

    public static void LogOut() {
        session().remove("sessionId");
    }

    public static void SignUp(User user) {

    }

    public static String getHash(String pass) {
        return null;
    }

    public static void makeNewSession(User user) {

    }

}

