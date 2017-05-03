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

    public static void logIn(String email, String pass) throws NoSuchFieldException, IllegalAccessException {
        User user = new User();
        if(user.find("mail", email) && user.passHash == getHash(pass)) {
            makeNewSession(user);
        } else {
            throw new NoSuchUserException();
        }
    }

    public static void LogOut() throws NoSuchFieldException, IllegalAccessException {
        if(session().containsKey("sessionId")) {
            String sessionId = session().get("sessionId");
            Session session = new Session();

            if(session.find("sessionId", session.sessionId)) {
                session.delete();
            }
            session().remove("sessionId");
        }
    }

    public static void SignUp(User user) throws NoSuchFieldException, IllegalAccessException {
        if(new User().find("mail", user.mail)) {
            throw new ThereIsSuchUserNameException();
        }
        user.save();
    }

    public static String getHash(String pass) {
        return null;
    }

    public static void makeNewSession(User user) {

    }

}

class NoSuchUserException extends RuntimeException {}

class ThereIsSuchUserNameException extends RuntimeException {}