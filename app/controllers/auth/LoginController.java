package controllers.auth;

import authorization.Authenticator;
import authorization.NoSuchUserException;
import authorization.models.UserForm;
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
        Form<UserForm> form = formFactory.form(UserForm.class);
        Http.Context context = Http.Context.current();
        return ok(views.html.auth_views.login.render(form, context.messages()));
    }

    public Result post() {
        Form<UserForm> form = formFactory.form(UserForm.class).bindFromRequest();
        Http.Context context = Http.Context.current();
        if(form.hasErrors()) {
            return ok(views.html.auth_views.login.render(form, context.messages()));
        }

        UserForm user = form.get();
        try {
            Authenticator.logIn(user);
        }
        catch (NoSuchUserException e) {
            form.reject("Authorization failed");
        }

        if(form.hasErrors()) {
            return ok(views.html.auth_views.login.render(form, context.messages()));
        }
        return ok(user.email + " " + user.pass);
    }
}
