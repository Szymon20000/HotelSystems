package controllers.booking;

import com.google.gson.GsonBuilder;
import com.google.gson.InstanceCreator;
import forms.SearchForm;
import forms.SearchResultForm;
import helpers.SessionMessages;
import models.Guest;
import models.Reservation;
import play.data.FormFactory;
import play.db.Database;
import play.mvc.Result;

import javax.inject.Inject;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.ArrayList;
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
        ArrayList<Guest> guestsList = new ArrayList<>();
        SearchForm searchForm = gsonBuilder.create().fromJson(session().get("searchResults"), SearchForm.class);
        for (int i = 0; i < searchForm.guests; ++i) {
            Guest guest = new Guest();
            guest.setEmail(session().get("email" + i));
            guest.setName(session().get("name" + i));
            guest.setPhone(session().get("phone" + i));
            guestsList.add(guest);
            session().remove("email" + i);
            session().remove("name" + i);
            session().remove("phone" + i);
        }

        //saving guests to database
        Guest guest = guestsList.get(0);
        guest.save();
        Integer bookerId = Guest.findGuest(guest.email).getId();
        for (int i = 1; i < guestsList.size(); ++i) {
            guest = guestsList.get(i);
            guest.setBooker(bookerId);
            guest.save();
        }

        Reservation reservation = new Reservation();
        SearchResultForm searchResultForm = gsonBuilder.create().fromJson(
                session().get("chosenRoom"),
                SearchResultForm.class
        );
        reservation.setIdRoom(searchResultForm.selectedRoom);
        reservation.setIdGuest(bookerId);
        reservation.setDateFrom(Date.valueOf(searchForm.startDate));
        reservation.setDateTo(Date.valueOf(searchForm.endDate));
        reservation.setIdNotification(1);
        reservation.save();
        return ok(views.html.booking_views.payment.render());
    }
}
