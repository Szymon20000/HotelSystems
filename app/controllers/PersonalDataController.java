package controllers;

import authorization.Authenticator;
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
        Form<Guests> form = formFactory.form(Guests.class);
        Http.Context context = Http.Context.current();
        return ok(views.html.personaldata.render(3,form, context.messages()));
    }

    public Result post() {
        DynamicForm requestData = formFactory.form().bindFromRequest();
        System.out.println(request().body().asFormUrlEncoded());
        System.out.println(requestData);
        for(int i=0;i<3;i++) {
            System.out.println(requestData.get("email"+i));
        }
        return ok("lol");
    }
}
