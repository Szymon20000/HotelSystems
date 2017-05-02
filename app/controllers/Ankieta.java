package controllers;


import play.db.Database;

/**
 * Created by szymon on 5/2/17.
 */
public class Ankieta extends Model {
    Ankieta() { }

    Ankieta(Integer id, String nazwisko, Integer wiek) {
        this.id = id;
        this.nazwisko = nazwisko;
        this.wiek = wiek;
    }

    Integer id;
    String nazwisko;
    Integer wiek;
}
