package controllers.booking;

import controllers.FormData;
import play.data.Form;
import play.data.FormFactory;
import play.db.Database;
import play.mvc.Http;
import play.mvc.Result;

import javax.inject.Inject;
import java.util.Map;
import java.util.TreeMap;

import static play.mvc.Results.ok;

public class SearchController {
    private Database db;
    private final play.data.FormFactory formFactory;

    @Inject
    public SearchController(Database db, FormFactory formFactory) {
        this.db = db;
        this.formFactory = formFactory;
    }

    public Result form() {
        Form<FormData> form = formFactory.form(FormData.class);
        Http.Context context = Http.Context.current();
        Map<String, String> a = new TreeMap<>();
        a.put("0", "Best");
        a.put("1", "Worst");
        return ok(views.html.booking_views.search.render(form, a, context.messages()));
    }
}
