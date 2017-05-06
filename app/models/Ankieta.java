package models;

public class Ankieta extends Model {
    public Ankieta() { }

    public Ankieta(Integer id, String nazwisko, Integer wiek) {
        this.id = id;
        this.nazwisko = nazwisko;
        this.wiek = wiek;
    }

    public Integer id;
    public String nazwisko;
    public Integer wiek;
}
