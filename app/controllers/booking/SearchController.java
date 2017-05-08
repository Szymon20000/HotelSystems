package controllers.booking;

import forms.SearchForm;
import models.Classes;
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

    private static Map<String, String> getRoomClassesMap() {
        Map<String, String> mapping = new TreeMap<>();
        List<Classes> roomClasses = Classes.findAll(Classes.class);
        for(Classes cls: roomClasses) {
            mapping.put(cls.getId().toString(), cls.getName());
        }

        return mapping;
    }

    public Result get() {
        Form<SearchForm> form = formFactory.form(SearchForm.class);
        Http.Context context = Http.Context.current();
        return ok(views.html.booking_views.search.render(form, getRoomClassesMap(), context.messages()));
    }

    public Result post() {
        Form<SearchForm> form = formFactory.form(SearchForm.class).bindFromRequest();
        Http.Context context = Http.Context.current();
        if(form.hasErrors()) {
            return ok(views.html.booking_views.search.render(form, getRoomClassesMap(), context.messages()));
        }
        SearchForm searchForm = form.get();
        return ok(views.html.booking_views.search.render(form, getRoomClassesMap(), context.messages()));
    }
}
