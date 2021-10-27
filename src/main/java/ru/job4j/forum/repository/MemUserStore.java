package ru.job4j.forum.repository;

import org.springframework.stereotype.Repository;
import ru.job4j.forum.model.User;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class MemUserStore implements UserStore {
    private Map<Integer, User> users = new ConcurrentHashMap<>();
    private AtomicInteger counter = new AtomicInteger();

    public void addNewUser(User user) {
        if (findUserByUsername(user.getUsername()) != null) {
            throw new IllegalArgumentException("Пользователь существует");
        }
        int id = counter.incrementAndGet();
        user.setId(id);
        users.put(id, user);
    }

    public User findUserByUsername(String username) {
        return users.values().stream().
                filter(u -> u.getUsername().equals(username)).findFirst().orElse(null);
    }
}
