package manager;

import user.User;

import java.util.HashMap;
import java.util.Map;

public class UserManager {
    private Map<String, User> users;

    public UserManager() {
        users = new HashMap<>();
    }

    public void registerUser(String username, String password) {
        if (!users.containsKey(username)) {
            users.put(username, new User(username, password));
        }
    }

    public User getUser(String username) {
        return users.get(username);
    }

}
