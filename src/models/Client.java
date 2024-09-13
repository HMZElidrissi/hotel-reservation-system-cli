package models;

import java.util.Map;

public class Client {
    private int id;
    private String name;
    private String email;
    private String phone;
    private Map<Long, Reservation> reservations;

    public Client(String name, String email, String phone) {
        this.name = name;
        this.email = email;
        this.phone = phone;
    }

    public Client(int id, String name, String email, String phone) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Map<Long, Reservation> getReservations() {
        return reservations;
    }

    public void setReservations(Map<Long, Reservation> reservations) {
        this.reservations = reservations;
    }
}
