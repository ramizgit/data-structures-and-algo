package lld.bookmyshow.model;

import java.time.LocalDateTime;
import java.util.List;

public class Show {

    int id;
    Movie movie;
    private Theatre theatre;
    Screen screen;
    LocalDateTime startTime;
    private LocalDateTime endTime;
    private List<ShowSeat> showSeats; //e.g. "A1" → ShowSeat(A1, AVAILABLE, ₹300); enables fast lookup by seat number when booking

    public Show(int id, Movie movie, Theatre theatre, Screen screen, LocalDateTime startTime, LocalDateTime endTime, List<ShowSeat> showSeats) {
        this.id = id;
        this.movie = movie;
        this.theatre = theatre;
        this.screen = screen;
        this.startTime = startTime;
        this.endTime = endTime;
        this.showSeats = showSeats;
    }

    public Show(int id, Movie movie, Screen screen, LocalDateTime startTime, List<ShowSeat> showSeats) {
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

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public List<ShowSeat> getShowSeats() {
        return showSeats;
    }

    public void setShowSeats(List<ShowSeat> showSeats) {
        this.showSeats = showSeats;
    }
}
