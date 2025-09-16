package repositories;

import java.util.HashMap;
import java.util.Map;

import models.User;

public class UserRepository {
    private static Map<String, User> users = new HashMap<>(); // key: email

    public static Map<String, User> all() {
        return users;
    }
}
