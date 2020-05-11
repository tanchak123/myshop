package myshop.service;

import myshop.model.User;

public interface UserService extends GenericService<User, Long> {

    User getByLogin(String login);

    void deleteByUser(User user);

    boolean userExisting(String login);
}
