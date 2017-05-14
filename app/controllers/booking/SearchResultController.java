package controllers.booking;

import models.Photo;
import models.Room;
import play.data.FormFactory;
import play.db.Database;
import play.mvc.Controller;
import play.mvc.Result;

import javax.inject.Inject;
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

    public Result index() throws NoSuchFieldException, IllegalAccessException, InstantiationException {
        List<Room> listOfRooms = Room.findAll(Room.class);
        List<Integer> photoIds = listOfRooms.stream().map(room -> room.idPhoto).collect(Collectors.toList());
        List<Photo> listOfPhotos = Photo.findAll("id", photoIds, Photo.class);
        Map<Integer, Photo> photosMap = listOfPhotos.stream().collect(Collectors.toMap(
                photo -> photo.id,
                photo -> photo
        ));
        return ok(views.html.booking_views.search_results.render(listOfRooms, photosMap));
    }
}
