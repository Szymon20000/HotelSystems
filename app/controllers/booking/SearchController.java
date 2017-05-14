package controllers.booking;

import forms.SearchForm;
import models.Standard;
import play.data.Form;
import play.data.FormFactory;
import play.db.Database;
import play.mvc.Http;
import play.mvc.Result;

import javax.inject.Inject;
import java.util.List;
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

    private static Map<String, String> getRoomStandardsMap() {
        Map<String, String> mapping = new TreeMap<>();
        List<Standard> roomStandards = Standard.findAll(Standard.class);
        for(Standard cls: roomStandards) {
            mapping.put(cls.getId().toString(), cls.getName());
        }

        return mapping;
    }

    public Result get() {
        Form<SearchForm> form = formFactory.form(SearchForm.class);
        Http.Context context = Http.Context.current();
        return ok(views.html.booking_views.search.render(form, getRoomStandardsMap(), context.messages()));
    }

    public Result post() {
        Form<SearchForm> form = formFactory.form(SearchForm.class).bindFromRequest();
        Http.Context context = Http.Context.current();
        if(form.hasErrors()) {
            return ok(views.html.booking_views.search.render(form, getRoomStandardsMap(), context.messages()));
        }
        SearchForm searchForm = form.get();
        return ok(views.html.booking_views.search.render(form, getRoomStandardsMap(), context.messages()));
    }
}
