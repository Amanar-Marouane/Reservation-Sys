package src.interfaces;

public interface AuthInterface {
    boolean register(String fullName, String email, String password);

    boolean login(String email, String password);

    void logout();
}