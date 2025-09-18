package src.helpers;

import src.Bootstrap;
import src.models.User;
import src.services.AuthService;
import src.utils.Console;

public class AuthHelper {
    private static AuthService auth = AuthService.getInstance();

    public static User shouldBeIn() {
        User u = auth.user();
        if (u == null) {
            Console.error("No user is currently logged in. Exiting...");
            Bootstrap.exit(1);
        }
        return u;
    }

    public static void shouldBeOut() {
        User u = auth.user();
        if (u != null) {
            Console.error("Currently in an active session, please logout first!");
            Bootstrap.exit(1);
        }
    }

}
