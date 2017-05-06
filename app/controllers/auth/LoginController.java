package controllers.auth;

import authorization.Authenticator;
import authorization.models.User;
import play.data.Form;
import play.data.FormFactory;
import play.db.Database;
import play.mvc.Http;
import play.mvc.Result;

import javax.inject.Inject;

import static play.mvc.Results.ok;

public class LoginController extends AuthController {
    @Inject
    public LoginController(Database db, FormFactory formFactory) {
        super(db, formFactory);
    }

    public Result get() {
        Form<User> form = formFactory.form(User.class);
        Http.Context context = Http.Context.current();
        return ok(views.html.auth_views.login.render(form, context.messages()));
    }

    public Result post() {
        Form<User> form = formFactory.form(User.class).bindFromRequest();
        Http.Context context = Http.Context.current();
        if(form.hasErrors()) {
            return ok(views.html.auth_views.login.render(form, context.messages()));
        }
        User user = form.get();
        user.setDb(db);
        Authenticator.logIn(user);
        return ok(user.email + " " + user.passHash);
    }
}
