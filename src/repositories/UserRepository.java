package src.repositories;

import java.util.*;
import src.interfaces.RepositoryBase;
import src.models.User;

public class UserRepository implements RepositoryBase<User> {

    private static final Map<String, User> users = new HashMap<>(); // key: email
    private static UserRepository instance;

    private UserRepository() {
    }

    public static UserRepository getInstance() {
        if (instance == null) {
            instance = new UserRepository();
        }
        return instance;
    }

    @Override
    public List<User> all() {
        return new ArrayList<>(users.values());
    }

    @Override
    public User find(String key, Object value) {
        if ("email".equalsIgnoreCase(key) && value instanceof String email) {
            return users.get(email);
        }
        return null;
    }

    @Override
    public void save(User user) {
        users.put(user.getEmail(), user);
    }

    @Override
    public void delete(String key, Object value) {
        // For now, only support deletion by "email"
        if ("email".equalsIgnoreCase(key) && value instanceof String email) {
            users.remove(email);
        }
    }
}
