package ru.job4j.forum.service;

import org.springframework.stereotype.Service;
import ru.job4j.forum.model.User;
import ru.job4j.forum.repository.UserStore;

@Service
public class AccessService {
    private UserStore userStore;

    public AccessService(UserStore userStore) {
        this.userStore = userStore;
    }

    public void saveUser(User user) {
        userStore.addNewUser(user);
    }

    public User verifyUser(User user) {
        User userInStore = userStore.findUserByUsername(user.getUsername());
        if (userInStore == null) {
            throw new IllegalArgumentException("Пользователь не существует.");
        }
        if (!userInStore.getPassword().equals(user.getPassword())) {
            throw new IllegalArgumentException("Неверный пароль.");
        }
        return userInStore;
    }
}
