package controllers.auth;

import authorization.Authenticator;
import authorization.UsernameAlreadyExistsException;
import authorization.models.UserForm;
import models.DatabaseException;
import play.data.Form;
import play.data.FormFactory;
import play.data.validation.ValidationError;
import play.db.Database;
import play.mvc.Http;
import play.mvc.Result;

import javax.inject.Inject;

import static play.mvc.Results.ok;

public class SignupController extends AuthController {
    @Inject
    public SignupController(Database db, FormFactory formFactory) {
        super(db, formFactory);
    }

    public Result get() {
        Form<UserForm> form = formFactory.form(UserForm.class);
        Http.Context context = Http.Context.current();
        return ok(views.html.auth_views.signup.render(form, context.messages()));
    }

    public Result post() {
        Form<UserForm> form = formFactory.form(UserForm.class).bindFromRequest();
        Http.Context context = Http.Context.current();
        if(form.hasErrors()) {
            return ok(views.html.auth_views.signup.render(form, context.messages()));
        }

        UserForm user = form.get();
        try {
            Authenticator.signUp(user);
        }
        catch (UsernameAlreadyExistsException e) {
            form.reject(new ValidationError("email", "E-mail is already taken."));
        }
        catch (DatabaseException e) {
            form.reject("Something went terribly wrong. Please retry.");
        }

        if(form.hasErrors()) {
            return ok(views.html.auth_views.signup.render(form, context.messages()));
        }
        return ok(user.email + " " + user.pass);
    }
}
