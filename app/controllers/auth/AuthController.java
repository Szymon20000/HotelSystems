package controllers.auth;

import play.data.FormFactory;
import play.db.Database;
import play.mvc.Controller;

import javax.inject.Inject;

public class AuthController extends Controller {
    Database db;
    final play.data.FormFactory formFactory;

    @Inject
    public AuthController(Database db, FormFactory formFactory) {
        this.db = db;
        this.formFactory = formFactory;
    }
}
