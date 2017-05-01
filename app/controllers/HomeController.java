package controllers;

import play.db.Database;
import play.mvc.*;

import javax.inject.Inject;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * This controller contains an action to handle HTTP requests
 * to the application's home page.
 */
public class HomeController extends Controller {

    private Database db;

    @Inject
    public HomeController(Database db) {
        this.db = db;
    }


    /**
     * An action that renders an HTML page with a welcome message.
     * The configuration in the <code>routes</code> file means that
     * this method will be called when the application receives a
     * <code>GET</code> request with a path of <code>/</code>.
     */
    public Result index() {
        Connection connection = db.getConnection();
        Statement stmt = null;

        int id = 5;
        int wiek= 5;
        String nazwisko = "temp";

        try {
            stmt = connection.createStatement();
            String sql = "SELECT * FROM ankiety";
            ResultSet rs = stmt.executeQuery(sql);

            while(rs.next()){
                id  = rs.getInt("id");
                wiek = rs.getInt("wiek");
                nazwisko = rs.getString("nazwisko");
            }

            rs.close();
            stmt.close();
        } catch (SQLException e) {
            nazwisko = e.getMessage();
            e.printStackTrace();
        }
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ok(views.html.index.render(id + " " + wiek + " " + nazwisko));
    }

}
