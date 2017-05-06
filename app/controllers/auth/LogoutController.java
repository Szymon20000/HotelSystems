package controllers.auth;

import authorization.Authenticator;
import authorization.models.UserForm;
import play.data.Form;
import play.data.FormFactory;
import play.db.Database;
import play.mvc.Http;
import play.mvc.Result;

import javax.inject.Inject;

import static play.mvc.Results.ok;

/**
 * Created by szymon on 5/6/17.
 */
public class LogoutController extends AuthController {
    @Inject
    public LogoutController(Database db, FormFactory formFactory) {
        super(db, formFactory);
    }

    public Result get() {
        Authenticator.logOut();
        return ok(views.html.auth_views.logout.render("You have successfully logged out!"));
    }
}
