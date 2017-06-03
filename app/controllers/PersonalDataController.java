package controllers;

import authorization.Authenticator;
import authorization.models.User;
import controllers.*;
import models.Guest;
import play.data.DynamicForm;
import play.data.FormFactory;
import play.db.Database;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;

import javax.inject.Inject;
import java.util.ArrayList;

public class PersonalDataController extends Controller {

    private Database db;
    private final play.data.FormFactory formFactory;

    @Inject
    public PersonalDataController(Database db, FormFactory formFactory) {
        this.db = db;
        this.formFactory = formFactory;
    }

    public Result get() {
        DynamicForm form = formFactory.form();
        Http.Context context = Http.Context.current();
        User user = Authenticator.getUser();
        Guest userGuest = Guest.find("email", user.getEmail(), Guest.class);
        return ok(views.html.personaldata.render(3, form, userGuest, context.messages()));
    }

    public Result post() {
        ArrayList<Guest> guestsList = new ArrayList<>();
        DynamicForm requestData = formFactory.form().bindFromRequest();
        Http.Context context = Http.Context.current();
        for (int i = 0; i < 3; ++i) {
            Guest guest = new Guest();
            guest.setEmail(requestData.get("email" + i));
            guest.setName(requestData.get("name" + i));
            guest.setPhone(requestData.get("phone" + i));
            guestsList.add(guest);
            session("email" + i, guest.getEmail());
            session("name" + i, guest.getName());
            session("phone" + i, guest.getPhone());
        }
        User user = User.find("email", guestsList.get(0).getEmail(), User.class);

        //redirecting to login page when user already exists
        if (Authenticator.getUser() == null && user != null) {
            session("referral", controllers.routes.PersonalDataController.get().url());
            return redirect(controllers.auth.routes.LoginController.get());
        }

        //redirecting to sign up page when when user with given email doesn't exists
        if (Authenticator.getUser() == null && user == null) {
            session("referral", controllers.routes.PersonalDataController.get().url());
            return redirect(controllers.auth.routes.SignupController.get());
        }

        return ok(views.html.datasubmitted.render(guestsList, formFactory.form(), context.messages()));
    }
}
