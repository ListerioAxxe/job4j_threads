package ru.job4j.userstore;

import net.jcip.annotations.ThreadSafe;
import java.util.HashMap;
import java.util.Map;


@ThreadSafe
public class UserStore implements UserStorage {

    private final Map<Integer, User> storage = new HashMap<>();

    @Override
    public synchronized boolean add(User user) {
       return storage.putIfAbsent(user.getId(), user) != null;
    }

    @Override
    public synchronized boolean update(User user) {
        return storage.computeIfPresent(user.getId(),
                (first, second) -> second = user) != null;
        }

    @Override
    public synchronized boolean delete(User user) {
        return storage.remove(user.getId(), user);
    }

    @Override
    public synchronized boolean transfer(int fromId, int toId, int amount) {
        User from = storage.get(fromId);
        User to = storage.get(toId);
        if (from != null && to != null && from.getAmount() >= amount) {
          return this.update(new User(fromId, from.getAmount() - amount))
                  && this.update(new User(toId, to.getAmount() + amount));
        }
        return false;
    }

    public synchronized User get(int id) {
        return storage.get(id);
    }
}
