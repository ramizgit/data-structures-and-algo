package lld.bookmyshow.model;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

public class Show {

    int id;
    Movie movie;
    Screen screen;
    LocalDateTime startTime;
    private Map<String, ShowSeat> showSeats;

    public Show(int id, Movie movie, Screen screen, LocalDateTime startTime, Map<String, ShowSeat> showSeats) {
        this.id = id;
        this.movie = movie;
        this.screen = screen;
        this.startTime = startTime;
        this.showSeats = showSeats;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public Screen getScreen() {
        return screen;
    }

    public void setScreen(Screen screen) {
        this.screen = screen;
    }

    public int getStartTime() {
        return startTime;
    }

    public void setStartTime(int startTime) {
        this.startTime = startTime;
    }

    public Map<String, ShowSeat> getShowSeats() {
        return showSeats;
    }

    public void setShowSeats(Map<String, ShowSeat> showSeats) {
        this.showSeats = showSeats;
    }
}
