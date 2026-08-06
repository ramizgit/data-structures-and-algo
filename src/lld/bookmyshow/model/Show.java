package lld.bookmyshow.model;

import java.time.LocalDateTime;
import java.util.Map;

public class Show {

    int id;
    Movie movie;
    Screen screen;
    LocalDateTime startTime;
    private Map<String, ShowSeat> seatsByNumber;

    public Show(int id, Movie movie, Screen screen, LocalDateTime startTime, Map<String, ShowSeat> seatsByNumber) {
        this.id = id;
        this.movie = movie;
        this.screen = screen;
        this.startTime = startTime;
        this.seatsByNumber = seatsByNumber;
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

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public Map<String, ShowSeat> getSeatsByNumber() {
        return seatsByNumber;
    }

    public void setSeatsByNumber(Map<String, ShowSeat> seatsByNumber) {
        this.seatsByNumber = seatsByNumber;
    }
}
