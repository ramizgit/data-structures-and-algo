package lld.bookmyshow.model;

public class Movie {

    int id;
    String name;
    int duration;
    String language;

    public Movie(int id, String name, int duration, String language) {
        this.id = id;
        this.name = name;
        this.duration = duration;
        this.language = language;
    }
}
