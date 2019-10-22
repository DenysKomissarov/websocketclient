package entities;

public class SocketUserModel {

    private String user_id;

    private String user_name;

    public SocketUserModel(String user_id, String user_name) {
        this.user_id = user_id;
        this.user_name = user_name;
    }

    public SocketUserModel() {
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }
    @Override
    public int hashCode() {
        return this.user_id.hashCode();
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

        return that.getUserId().equals(this.user_id);
    }

    @Override
    public String toString() {
        return "SocketUserModel{" +
                "user_id='" + user_id + '\'' +
                ", user_name='" + user_name + '\'' +
                '}';
    }
}
