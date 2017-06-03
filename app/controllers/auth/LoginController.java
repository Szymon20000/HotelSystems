package controllers.auth;

import authorization.Authenticator;
import authorization.NoSuchUserException;
import authorization.models.UserForm;
import helpers.SessionMessages;
import play.data.Form;
import play.data.FormFactory;
import play.db.Database;
import play.mvc.Http;
import play.mvc.Result;

import javax.inject.Inject;

import static play.mvc.Results.ok;
import static play.mvc.Results.redirect;

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

        SessionMessages.addSuccess("You have successfully logged in!");
        String referral = session().remove("referral");
        if(referral == null){
            return redirect(controllers.booking.routes.SearchController.get());
        }
        else {
            return redirect(referral);
        }
    }
}
