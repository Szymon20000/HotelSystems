package controllers.guest_panel;

import authorization.Authenticator;
import authorization.UsernameAlreadyExistsException;
import authorization.models.UserForm;
import helpers.SessionMessages;
import models.DatabaseException;
import models.Message;
import play.data.Form;
import play.data.FormFactory;
import play.data.validation.ValidationError;
import play.db.Database;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;

import javax.inject.Inject;
import java.util.Date;

/**
 * Created by szymon on 5/8/17.
 */
public class ContactController  extends Controller {
    private final play.data.FormFactory formFactory;

    @Inject
    public ContactController(Database db, FormFactory formFactory) {
        this.formFactory = formFactory;
    }

    public Result get() {
        Form<Message> form = formFactory.form(Message.class);
        Http.Context context = Http.Context.current();
        return ok(views.html.guest_panel_views.contact.render(form, context.messages()));
    }

    public Result post() {
        Form<Message> form = formFactory.form(Message.class).bindFromRequest();
        Http.Context context = Http.Context.current();
        if(form.hasErrors()) {
            return ok(views.html.guest_panel_views.contact.render(form, context.messages()));
        }

        Message message = form.get();

        if(form.hasErrors()) {
            return ok(views.html.guest_panel_views.contact.render(form, context.messages()));
        }
        message.date = new Date().getTime();
        message.sender = Authenticator.getUser().id;
        message.addressee = Message.getManagerId();
      //message.save(); ToDo create table for message
        SessionMessages.addSuccess("Your message has been sent!");
        return ok(views.html.guest_panel_views.contact.render(form, context.messages()));
    }
}
