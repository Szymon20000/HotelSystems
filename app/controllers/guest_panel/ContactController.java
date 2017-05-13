package controllers.guest_panel;

import authorization.Authenticator;
import helpers.SessionMessages;
import models.Message;
import models.Model;
import play.data.Form;
import play.data.FormFactory;
import play.db.Database;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ContactController  extends Controller {
    private final play.data.FormFactory formFactory;

    @Inject
    public ContactController(Database db, FormFactory formFactory) {
        this.formFactory = formFactory;
    }

    public Result get() {
        Form<Message> form = formFactory.form(Message.class);
        Http.Context context = Http.Context.current();
        List<MessageWrapper> messageWrapperList =  MessageWrapper.getHistory(Authenticator.getUser().id);
        return ok(views.html.guest_panel_views.contact.render(form, messageWrapperList, context.messages()));
    }

    public Result post() {
        Form<Message> form = formFactory.form(Message.class).bindFromRequest();
        Http.Context context = Http.Context.current();
        if(form.hasErrors()) {
            List<MessageWrapper> messageWrapperList =  MessageWrapper.getHistory(Authenticator.getUser().id);
            return ok(views.html.guest_panel_views.contact.render(form, messageWrapperList, context.messages()));
        }

        Message message = form.get();
        message.date = new Date().getTime();
        message.sender = Authenticator.getUser().id;
        message.addressee = Message.getManagerId();
        message.save();
        SessionMessages.addSuccess("Your message has been sent!");
        return redirect(controllers.guest_panel.routes.ContactController.get());
    }


    static public class MessageWrapper {
        public Message message;
        public boolean isMine;

        MessageWrapper() {}

        MessageWrapper(Message message, boolean isMine) {
            this.message = message;
            this.isMine = isMine;
        }

        static List<MessageWrapper> getHistory(Integer id) {
            List<Message> mineMessages = Model.<Message>findAll("sender", id, Message.class);
            List<Message> addressedToMe = Model.<Message>findAll("addressee", id, Message.class);

            List<MessageWrapper> res = new ArrayList<>();

            for(Message message : mineMessages) {
                res.add(new MessageWrapper(message, true));
            }

            for(Message message : addressedToMe) {
                res.add(new MessageWrapper(message, false));
            }

            res.sort((x,y) -> y.message.date.compareTo(x.message.date) );

            return res;
        }
    }
}
