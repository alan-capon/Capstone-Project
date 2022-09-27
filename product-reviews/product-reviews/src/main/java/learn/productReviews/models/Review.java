package learn.productReviews.models;

import java.time.LocalDate;

public class Review {

    private int id;
    private int appUserId;
    private int productId;
    private LocalDate date;
    private String content;

    public Review() {
    }

    public Review(int id, int appUserId, int productId, LocalDate date, String content) {
        this.id = id;
        this.appUserId = appUserId;
        this.productId = productId;
        this.date = date;
        this.content = content;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAppUserId() {
        return appUserId;
    }

    public void setAppUserId(int appUserId) {
        this.appUserId = appUserId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
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
