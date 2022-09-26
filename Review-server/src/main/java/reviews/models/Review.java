package reviews.models;

import java.time.LocalDate;

public class Review {

    private int id;
    private AppUser user;
    private LocalDate date;
    private String content;

    public Review() {
    }

    public Review(int id, AppUser user, LocalDate date, String content) {
        this.id = id;
        this.user = user;
        this.date = date;
        this.content = content;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public AppUser getUser() {
        return user;
    }

    public void setUser(AppUser user) {
        this.user = user;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}