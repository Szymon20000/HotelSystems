package controllers;

import authorization.Authenticator;
import authorization.models.User;
import controllers.guest_panel.ContactController;
import models.Guests;
import models.Message;
import play.data.DynamicForm;
import play.data.Form;
import play.data.FormFactory;
import play.db.Database;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

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
        Guests userGuest=null;
        try {
            userGuest = Guests.find("mail", user.getEmail(), Guests.class);
        } catch (NullPointerException | IllegalAccessException | NoSuchFieldException | InstantiationException e){}
        return ok(views.html.personaldata.render(2, form, userGuest, context.messages()));
    }

    public Result post() {
        ArrayList<Guests> guestsList = new ArrayList<>();
        DynamicForm requestData = formFactory.form().bindFromRequest();
        Http.Context context = Http.Context.current();
        for(int i=0;i<2;i++) {
            Guests guest = new Guests();
            guest.setMail(requestData.get("email"+i));
            guest.setName(requestData.get("name"+i));
            guest.setPhone(requestData.get("phone"+i));
            guestsList.add(guest);
        }
        return ok(views.html.personaldatasubmitted.render(guestsList, formFactory.form(), context.messages()));
    }
}
