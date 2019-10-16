package messages.http;

public class CreateUserDto {

    public String firstname = "firstname";

    public String lastname = "lastname";

    public String accountId;

    public String password = "password";

    public CreateUserDto(int id) {
        this.firstname = this.firstname + id;
        this.lastname = this.lastname + id;
        this.accountId = this.firstname + this.lastname + "@gmail.com";
    }
}
