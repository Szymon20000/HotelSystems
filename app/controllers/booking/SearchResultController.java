package controllers.booking;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.InstanceCreator;
import forms.SearchForm;
import forms.SearchResultForm;
import models.Photo;
import models.Room;
import play.data.Form;
import play.data.FormFactory;
import play.db.Database;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;

import javax.inject.Inject;
import java.math.BigDecimal;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class SearchResultController extends Controller {

    private Database db;
    private final play.data.FormFactory formFactory;

    @Inject
    public SearchResultController(Database db, FormFactory formFactory) {
        this.db = db;
        this.formFactory = formFactory;
    }

    public Result get() throws NoSuchFieldException, IllegalAccessException, InstantiationException {
        if (!session().containsKey("searchResults"))
            return redirect(controllers.booking.routes.SearchController.get());

        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(
                Comparator.class,
                (InstanceCreator<Comparator<BigDecimal>>) type -> BigDecimal::compareTo
        );
        SearchForm form = gsonBuilder.create().fromJson(session().get("searchResults"), SearchForm.class);

        List<Room> listOfRooms = Room.findAll(Room.class);
        listOfRooms = listOfRooms.stream().filter(room -> {
            if(!form.priceRange.contains(room.getPrice()))
                return false;
            if(form.guests.compareTo(room.getNumberOfBeds()) > 0)
                return false;
            if(form.roomStandard.compareTo(room.getIdStandard()) != 0)
                return false;
            return true;
        }).collect(Collectors.toList());

        List<Integer> photoIds = listOfRooms.stream().map(room -> room.idPhoto).collect(Collectors.toList());
        List<Photo> listOfPhotos = Photo.findAll("id", photoIds, Photo.class);
        Map<Integer, Photo> photosMap = listOfPhotos.stream().collect(Collectors.toMap(
                photo -> photo.id,
                photo -> photo
        ));

        Form<SearchResultForm> resultForm = formFactory.form(SearchResultForm.class);
        Http.Context context = Http.Context.current();

        return ok(views.html.booking_views.search_results.render(listOfRooms, photosMap, resultForm, context.messages()));
    }

    public Result post() {
        if (!session().containsKey("searchResults"))
            return redirect(controllers.booking.routes.SearchController.get());

        Form<SearchResultForm> form = formFactory.form(SearchResultForm.class).bindFromRequest();
        if(form.hasErrors()) {
            return redirect(controllers.booking.routes.SearchResultController.get());
        }
        SearchResultForm searchResultForm = form.get();
        session().put("chosenRoom", new Gson().toJson(searchResultForm));
        return redirect(controllers.booking.routes.PersonalDataController.get());
    }
}
