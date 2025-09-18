package src.interfaces;

import src.models.User;

public interface AuthInterface {
    boolean register(String fullName, String email, String password);

    boolean login(String email, String password);

    void logout();

    User user();

    boolean isLogged();
}