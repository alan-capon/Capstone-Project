package learn.productReviews.models;

import java.time.LocalDate;

public class Review {

    private int id;
    private AppUser user;
    private Product product;
    private LocalDate date;
    private String content;

    public Review() {
    }

    public Review(int id, AppUser user, Product product, LocalDate date, String content) {
        this.id = id;
        this.user = user;
        this.product = product;
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

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
