package controllers.auth;

import play.data.FormFactory;
import play.db.Database;

import javax.inject.Inject;

public class AuthController {
    Database db;
    final play.data.FormFactory formFactory;

    @Inject
    public AuthController(Database db, FormFactory formFactory) {
        this.db = db;
        this.formFactory = formFactory;
    }
}
