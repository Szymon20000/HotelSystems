package controllers;

import models.Model;
import models.Photo;
import models.Room;
import play.data.FormFactory;
import play.db.Database;
import play.mvc.Controller;
import play.mvc.Result;

import javax.inject.Inject;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
public class ShowRooms extends Controller {

    private Database db;
    private final play.data.FormFactory formFactory;

    @Inject
    public ShowRooms(Database db, FormFactory formFactory) {
        this.db = db;
        this.formFactory = formFactory;
    }

    /**
     * An action that renders an HTML page with a welcome message.
     * The configuration in the <code>routes</code> file means that
     * this method will be called when the application receives a
     * <code>GET</code> request with a path of <code>/</code>.
     */
    public Result index() throws NoSuchFieldException, IllegalAccessException, InstantiationException {
        List<Room> ListOfRoom = Model.<Room>findAll(Room.class);
        Room room1 = new Room();
        Room room2 = new Room();
        Room room3 = new Room();
        room1.setPrice(BigDecimal.valueOf(1000));
        room2.setPrice(BigDecimal.valueOf(1000));
        room3.setPrice(BigDecimal.valueOf(1500));
        room1.setId(10);
        room2.setId(20);
        room3.setId(30);
        room1.setFloor(1);
        room2.setFloor(2);
        room3.setFloor(3);
        room1.setIdPhoto(1);
        room2.setIdPhoto(2);
        room3.setIdPhoto(3);
        room1.setNumberOfBeds(2);
        room2.setNumberOfBeds(2);
        room3.setNumberOfBeds(3);
        ListOfRoom.add(room1);
        ListOfRoom.add(room2);
        ListOfRoom.add(room3);
        System.out.println(ListOfRoom);

        List<Photo> ListOfPhoto = Model.<Photo>findAll(Photo.class);
        Photo photo1 = new Photo();
        Photo photo2 = new Photo();
        Photo photo3 = new Photo();
        photo1.setId(1);
        photo2.setId(2);
        photo3.setId(3);
        photo1.setFilepath("assets/images/room.jpg");
        photo2.setFilepath("assets/images/room1.jpg");
        photo3.setFilepath("assets/images/room2.jpg");
        ListOfPhoto.add(photo1);
        ListOfPhoto.add(photo2);
        ListOfPhoto.add(photo3);
        return ok(views.html.rooms.render(ListOfRoom, ListOfPhoto));
    }
}