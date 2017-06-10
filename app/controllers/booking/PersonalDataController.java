package controllers.booking;

import authorization.Authenticator;
import authorization.models.User;
import com.google.gson.GsonBuilder;
import com.google.gson.InstanceCreator;
import forms.SearchForm;
import forms.SearchResultForm;
import models.Guest;
import models.Room;
import play.data.DynamicForm;
import play.data.FormFactory;
import play.db.Database;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;

import javax.inject.Inject;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Comparator;

public class PersonalDataController extends Controller {

    private Database db;
    private final play.data.FormFactory formFactory;

    @Inject
    public PersonalDataController(Database db, FormFactory formFactory) {
        this.db = db;
        this.formFactory = formFactory;
    }

    private static GsonBuilder getGsonBuilder() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(
                Comparator.class,
                (InstanceCreator<Comparator<BigDecimal>>) type -> BigDecimal::compareTo
        );
        return gsonBuilder;
    }

    public Result get() {
        if (!session().containsKey("searchResults") || !session().containsKey("chosenRoom"))
            return redirect(controllers.booking.routes.SearchController.get());

        GsonBuilder gsonBuilder = getGsonBuilder();
        SearchForm searchForm = gsonBuilder.create().fromJson(session().get("searchResults"), SearchForm.class);
        SearchResultForm searchResultForm = gsonBuilder.create().fromJson(
                session().get("chosenRoom"),
                SearchResultForm.class
        );

        DynamicForm form = formFactory.form();
        Http.Context context = Http.Context.current();
        User user = Authenticator.getUser();
        Guest userGuest = null;
        if (user != null) {
            userGuest = Guest.findGuest(user.getEmail());
        }
        return ok(views.html.booking_views.personaldata.render(searchForm.guests, form, userGuest, context.messages()));
    }

    public Result post() {
        if (!session().containsKey("searchResults") || !session().containsKey("chosenRoom"))
            return redirect(controllers.booking.routes.SearchController.get());

        GsonBuilder gsonBuilder = getGsonBuilder();
        SearchForm searchForm = gsonBuilder.create().fromJson(session().get("searchResults"), SearchForm.class);
        SearchResultForm searchResultForm = gsonBuilder.create().fromJson(
                session().get("chosenRoom"),
                SearchResultForm.class
        );

        ArrayList<Guest> guestsList = new ArrayList<>();
        DynamicForm requestData = formFactory.form().bindFromRequest();
        Http.Context context = Http.Context.current();
        for (int i = 0; i < searchForm.guests; ++i) {
            Guest guest = new Guest();
            guest.setEmail(requestData.get("email" + i));
            guest.setName(requestData.get("name" + i));
            guest.setPhone(requestData.get("phone" + i));
            guestsList.add(guest);
            session("email" + i, guest.getEmail());
            session("name" + i, guest.getName());
            session("phone" + i, guest.getPhone());
        }
        User user = User.find("email", guestsList.get(0).getEmail(), User.class);

        //redirecting to login page when user already exists
        if (Authenticator.getUser() == null && user != null) {
            session("referral", controllers.booking.routes.PersonalDataController.get().url());
            return redirect(controllers.auth.routes.LoginController.get());
        }

        //redirecting to sign up page when when user with given email doesn't exists
        if (Authenticator.getUser() == null && user == null) {
            session("signupEmail", guestsList.get(0).getEmail());
            session("referral", controllers.booking.routes.PersonalDataController.get().url());
            return redirect(controllers.auth.routes.SignupController.get());
        }

        Room selectedRoom = Room.find("id", searchResultForm.selectedRoom, Room.class);
        BigDecimal price = selectedRoom.getPrice().multiply(BigDecimal.valueOf(searchForm.guests));
        return ok(views.html.booking_views.booking_summary.render(
                guestsList,
                formFactory.form(),
                searchForm,
                searchResultForm,
                price,
                context.messages()
        ));
    }
}
