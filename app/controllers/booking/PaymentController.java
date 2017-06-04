package controllers.booking;

import com.google.gson.GsonBuilder;
import com.google.gson.InstanceCreator;
import forms.SearchForm;
import helpers.SessionMessages;
import play.data.FormFactory;
import play.db.Database;
import play.mvc.Result;

import javax.inject.Inject;

import java.math.BigDecimal;
import java.util.Comparator;

import static play.mvc.Controller.session;
import static play.mvc.Results.ok;

public class PaymentController {
    private Database db;
    private final play.data.FormFactory formFactory;

    @Inject
    public PaymentController(Database db, FormFactory formFactory) {
        this.db = db;
        this.formFactory = formFactory;
    }

    public Result post() {
        SessionMessages.addSuccess("Your payment was successful!");
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(
                Comparator.class,
                (InstanceCreator<Comparator<BigDecimal>>) type -> BigDecimal::compareTo
        );
        SearchForm searchForm = gsonBuilder.create().fromJson(session().get("searchResults"), SearchForm.class);
        for (int i = 0; i < searchForm.guests; ++i) {
            session().remove("email" + i);
            session().remove("name" + i);
            session().remove("phone" + i);
        }
        return ok(views.html.booking_views.payment.render());
    }
}
