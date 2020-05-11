package myshop.service.security;

import myshop.model.User;

public interface AuthenticationService {

    boolean existence(String login);

    User getByLogin(String login);
}
