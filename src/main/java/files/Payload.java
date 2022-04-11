package files;

import java.util.Random;

public class Payload {

    private final String email = generateEmail();
    private final String password = generatePassword();

    private int generateRandomIntNumber() {
        Random ran = new Random();
        return ran.nextInt();
    }

    private String generateEmail() {
        String email;
        return email = "daniel.meschian+" + generateRandomIntNumber() + "@mentormate.com";
    }

    private String generatePassword() {
        String password;
        return password = "d5QdKpNZDTYF2Y6" + generateRandomIntNumber();
    }

    public String getEmail() {
        return this.email;
    }

    public String getPassword() {
        return this.password;
    }

    public String registerNewUser() {
        String currentPass = this.password;
        return String.format("{\r\n" +
                "  \"first_name\": \"Filomena\",\r\n" +
                "  \"last_name\": \"Dare\",\r\n" +
                "  \"email\": \"%s\",\r\n" +
                "  \"password\": \"%s\",\r\n" +
                "  \"password_confirmation\": \"%s\"\r\n" +
                "}", getEmail(), currentPass, currentPass);
    }
    public String login() {
        return String.format("{\r\n" +
                "  \"email\": \"%s\",\r\n" +
                "  \"password\": \"%s\",\r\n" +
                "}", getEmail(), getPassword());
    }
}
