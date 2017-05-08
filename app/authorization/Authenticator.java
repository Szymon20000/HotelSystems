package authorization;

import authorization.models.Session;
import authorization.models.User;
import authorization.models.UserForm;
import models.DatabaseException;
import org.mindrot.jbcrypt.BCrypt;

import java.util.Date;

import static play.mvc.Controller.session;

public class Authenticator {

    public static User getUser() throws NoSuchFieldException, IllegalAccessException {
        if(session().containsKey("sessionId")) {
            String sessionId = session().get("sessionId");
            Session session = new Session();

            if(session.load("sessionId", sessionId)) {
                User user = new User();
                user.loadById(session.userId);
                return user;
            }
        }
        return null;
    }

    public static void logIn(UserForm user) {
        try {
            User dbUser = User.find("email", user.getEmail(), User.class);
            if(dbUser != null && checkPass(user.getPass(), dbUser.getPassHash())) {
                makeNewSession(dbUser);
            } else {
                throw new NoSuchUserException();
            }
        } catch (NoSuchFieldException | IllegalAccessException | InstantiationException e) {
            throw new NoSuchUserException();
        }
    }

    public static void logOut() {
        try {
            if(session().containsKey("sessionId")) {
                String sessionId = session().get("sessionId");
                Session session = new Session();

                if(session.load("sessionId", sessionId)) {
                    session.delete();
                }
                session().remove("sessionId");
            }
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new DatabaseException();
        }
    }

    public static void signUp(UserForm user) {
        try {
            if(new User().load("email", user.getEmail())) {
                throw new UsernameAlreadyExistsException();
            }
            user.convertToUser().save();
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new DatabaseException();
        }
    }

    public static String getHash(String pass) {
        return BCrypt.hashpw(pass, BCrypt.gensalt());
    }

    public static boolean checkPass(String pass, String passHash) {
        return BCrypt.checkpw(pass, passHash);
    }

    public static void makeNewSession(User user) throws NoSuchFieldException, IllegalAccessException {
        final int LEN_OF_SESSION_ID = 20;
        final long TEN_DAYS = 1000*3600*24*10;
        Session session = new Session(0, "0", user.id, new Date().getTime() + TEN_DAYS);
        session.sessionId = session.getNewId(LEN_OF_SESSION_ID);
        session.save();
        session().put("sessionId", session.sessionId);
    }

}
