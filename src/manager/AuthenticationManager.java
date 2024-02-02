package manager;

import user.User;

public class AuthenticationManager {
    private UserManager userManager;

    public AuthenticationManager(UserManager userManager) {
        this.userManager = userManager;
    }

    public boolean login(String username, String password) {
        User user = userManager.getUser(username);
        return user != null && user.getPassword().equals(password);
    }

}
