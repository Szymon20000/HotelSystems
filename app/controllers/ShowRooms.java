package controllers;

import models.Model;
import models.Rooms;
import play.data.FormFactory;
import play.db.Database;
import play.mvc.Controller;
import play.mvc.Result;

import javax.inject.Inject;
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
        List<Rooms> ListOfRooms = Model.<Rooms>findAll(Rooms.class);
        Rooms room1 = new Rooms();
        Rooms room2 = new Rooms();
        room1.setId(1);
        room2.setId(2);
        room1.setFloor(3);
        room2.setFloor(2);
        ListOfRooms.add(room1);
        ListOfRooms.add(room2);
        System.out.println(ListOfRooms);
        return ok(views.html.rooms.render((ArrayList<Rooms>) ListOfRooms));
    }
}