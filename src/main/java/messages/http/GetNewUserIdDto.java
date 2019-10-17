package messages.http;

public class GetNewUserIdDto {
    public String status;
    public String userId;

    public GetNewUserIdDto() {
    }

    public GetNewUserIdDto(String status, String userId) {
        this.status = status;
        this.userId = userId;
    }
}
