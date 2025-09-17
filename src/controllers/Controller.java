package src.controllers;

import src.enums.Roles;
import src.models.User;

abstract public class Controller {

    public boolean onlyRole(User u, Roles r) {
        return u.getRole() == r;
    }

    public boolean onlyRoles(User u, Roles... roles) {
        for (Roles r : roles) {
            if (u.getRole() == r)
                return true;
        }
        return false;
    }

    public boolean onlyAdmin(User u) {
        return onlyRole(u, Roles.ADMIN);
    }

    public boolean onlyUser(User u) {
        return onlyRole(u, Roles.USER);
    }
}
