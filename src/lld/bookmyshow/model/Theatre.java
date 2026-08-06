package lld.bookmyshow.model;

import java.util.List;

public class Theatre {

    int id;
    String name;
    String city;
    String address;
    List<Screen> screens;

    public Theatre(int id, String name, String city, String address, List<Screen> screens) {
        this.id = id;
        this.name = name;
        this.city = city;
        this.address = address;
        this.screens = screens;
    }
}
