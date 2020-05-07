package myshop.service.impl;

import myshop.dao.BucketDao;
import myshop.dao.UserDao;
import myshop.lib.Inject;
import myshop.lib.Service;
import myshop.model.Bucket;
import myshop.model.User;
import myshop.service.UserService;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class UserServiceImpl implements UserService {
    @Inject
    private UserDao userDao;

    @Inject
    private BucketDao bucketDao;

    @Override
    public void create(User user) {
        userDao.create(user);
        bucketDao.create(new Bucket(user));
    }

    @Override
    public User get(Long id) {
        return userDao.get(id);
    }

    @Override
    public User getByLogin(String login) {
        return userDao.get(userDao.getAll().stream()
        .filter(user -> user.getLogin().equals(login))
        .findFirst()
        .orElseThrow(() ->
                new NoSuchElementException("Такого пользователя не существует :("))
        .getId());
    }

    @Override
    public List<User> getAll() {
        return userDao.getAll();
    }

    @Override
    public void update(User user) {
        userDao.update(user);
    }

    @Override
    public void delete(Long id) {
        userDao.delete(id);
        bucketDao.delete(id);
    }

    @Override
    public void deleteByUser(User user) {
        userDao.delete(user.getId());
    }
}
