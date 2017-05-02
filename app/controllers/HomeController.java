package controllers;

import play.data.Form;
import play.data.FormFactory;
import play.db.Database;
import play.mvc.*;

import javax.inject.Inject;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * This controller contains an action to handle HTTP requests
 * to the application's home page.
 */
public class HomeController extends Controller {

    private Database db;
    private final play.data.FormFactory formFactory;

    @Inject
    public HomeController(Database db, FormFactory formFactory) {
        this.db = db;
        this.formFactory = formFactory;
    }

    public Result form() {
        Form<FormData> form = formFactory.form(FormData.class);
        Http.Context context = Http.Context.current();
        return ok(views.html.form.render(form, context.messages()));
    }

    public Result upload() {
        Form<FormData> formData = formFactory.form(FormData.class).bindFromRequest();
        if(formData.hasErrors()) {
            return badRequest("nope");
        }
        FormData data = formData.get();
        return ok(data.name + " " + data.password);
    }

    /**
     * An action that renders an HTML page with a welcome message.
     * The configuration in the <code>routes</code> file means that
     * this method will be called when the application receives a
     * <code>GET</code> request with a path of <code>/</code>.
     */
    public Result index() throws NoSuchFieldException, IllegalAccessException {
        Ankieta ankieta = new Ankieta(8, "Nowaczek", 28);
        ankieta.setDb(db);
        ankieta.save();
        ankieta.wiek = 6;
        ankieta.save();
        ankieta.get(5);
        return ok(views.html.index.render(ankieta.id + " " + ankieta.nazwisko + " " + ankieta.wiek));
    }

}
