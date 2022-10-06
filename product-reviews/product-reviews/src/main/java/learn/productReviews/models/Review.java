package learn.productReviews.models;

import java.time.LocalDate;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Review review = (Review) o;
        return getAppUserId() == review.getAppUserId() && getProductId() == review.getProductId() && Objects.equals(getDate(), review.getDate()) && Objects.equals(getContent(), review.getContent());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getAppUserId(), getProductId(), getDate(), getContent());
    }
}
