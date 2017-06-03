package controllers.booking;

import helpers.SessionMessages;
import play.data.FormFactory;
import play.db.Database;
import play.mvc.Result;

import javax.inject.Inject;

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
        return ok(views.html.booking_views.payment.render());
    }
}
