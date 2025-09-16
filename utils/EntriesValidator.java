package utils;

public class EntriesValidator {

    public boolean isValidEmail(String email) {
        return email != null && email.matches("^[A-Za-z0-9+_.-]+@(.+)$");
    }

    public boolean isValidFullName(String fullName) {
        return fullName != null && fullName.trim().length() >= 2;
    }

    public boolean isValidPassword(String password) {
        return password != null && password.length() >= 6;
    }
}
