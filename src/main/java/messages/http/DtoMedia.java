package messages.http;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

public class DtoMedia {

    private String id;

    private Integer categoryId;

    private String name;

    private Integer verified;

    private Integer published;

    private Double cost;

    private String userId;

    private Double rating;

    private String description;

    private Integer active;

    private String promoCode;

    public DtoMedia() {
    }

    public DtoMedia(String id, Integer categoryId, String name, Integer verified, Integer published, Double cost, String userId, Double rating, String description, Integer active, String promoCode) {
        this.id = id;
        this.categoryId = categoryId;
        this.name = name;
        this.verified = verified;
        this.published = published;
        this.cost = cost;
        this.userId = userId;
        this.rating = rating;
        this.description = description;
        this.active = active;
        this.promoCode = promoCode;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getVerified() {
        return verified;
    }

    public void setVerified(Integer verified) {
        this.verified = verified;
    }

    public Integer getPublished() {
        return published;
    }

    public void setPublished(Integer published) {
        this.published = published;
    }

    public Double getCost() {
        return cost;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getActive() {
        return active;
    }

    public void setActive(Integer active) {
        this.active = active;
    }

    public String getPromoCode() {
        return promoCode;
    }

    public void setPromoCode(String promoCode) {
        this.promoCode = promoCode;
    }
}
