package interfaces;

import java.util.Map;

import models.User;

public interface AuthInterface {
    boolean register(Map<String, User> users, String fullName, String email, String password);

    boolean login(Map<String, User> users, String email, String password);

    void logout();
}