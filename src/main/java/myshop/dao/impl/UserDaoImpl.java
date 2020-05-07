package myshop.dao.impl;

import myshop.dao.UserDao;
import myshop.lib.Dao;
import myshop.model.User;
import myshop.storage.Storage;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.IntStream;

@Dao
public class UserDaoImpl implements UserDao {

    @Override
    public void create(User user) {
        Storage.users.add(user);
    }

    @Override
    public void update(User user) {
        IntStream.range(0, Storage.users.size())
                .filter(i -> Storage.users.get(i).getId().equals(user.getId()))
                .forEach(i -> Storage.users.set(i, user));
    }

    @Override
    public User get(Long id) {
        return Storage.users.stream()
                .filter(i -> i.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("Такого айди не существует!"));
    }

    @Override
    public List<User> getAll() {
        return Storage.users;
    }

    @Override
    public void delete(Long id) {
        Storage.users.removeIf(x -> x.getId().equals(id));
    }
}
