package entities;

public class User {

//    @JsonProperty("user_id")
    private String userId;
//    @JsonProperty("user_name")
    private String listenName;

    public User(String userId, String listenName) {
        this.userId = userId;
        this.listenName = listenName;
    }

    public User(String userId) {
        this.userId = userId;
    }

    public String getListenName() {
        return listenName;
    }

    public void setListenName(String listenName) {
        this.listenName = listenName;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Override
    public int hashCode() {
        return this.userId.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof User)) {
            return false;
        }
        User that = (User) obj;

        return that.getUserId().equals(this.userId);
    }
}
