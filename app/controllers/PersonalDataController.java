package controllers;

import authorization.Authenticator;
import authorization.models.User;
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
        Guest userGuest=null;
        try {
            userGuest = Guest.find("mail", user.getEmail(), Guest.class);
        } catch (NullPointerException | IllegalAccessException | NoSuchFieldException | InstantiationException e){}
        return ok(views.html.personaldata.render(2, form, userGuest, context.messages()));
    }

    public Result post() {
        ArrayList<Guest> guestsList = new ArrayList<>();
        DynamicForm requestData = formFactory.form().bindFromRequest();
        Http.Context context = Http.Context.current();
        for(int i=0;i<2;i++) {
            Guest guest = new Guest();
            guest.setMail(requestData.get("email"+i));
            guest.setName(requestData.get("name"+i));
            guest.setPhone(requestData.get("phone"+i));
            guestsList.add(guest);
        }
        return ok(views.html.datasubmitted.render(guestsList, formFactory.form(), context.messages()));
    }
}
