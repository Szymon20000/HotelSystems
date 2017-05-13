package controllers;

import helpers.SessionMessages;
import models.Guests;
import play.data.DynamicForm;
import play.data.FormFactory;
import play.db.Database;
import play.mvc.Http;
import play.mvc.Result;

import javax.inject.Inject;
import java.util.ArrayList;

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
        return ok(views.html.payment.render());
    }
}
