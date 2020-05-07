package myshop.service.security;

import myshop.lib.Inject;
import myshop.lib.Injector;
import myshop.lib.Service;
import myshop.model.User;
import myshop.service.UserService;

@Service
public class AuthnticationServiceImpl implements AuthenticationService{
    @Inject
    private UserService userService;

    @Override
    public boolean existence(String login) {
        return userService.getAll().stream()
                .anyMatch(user1 -> user1.getLogin().equals(login));
    }

    @Override
    public User getByLogin(String login) {
        return userService.getByLogin(login);
    }
}
