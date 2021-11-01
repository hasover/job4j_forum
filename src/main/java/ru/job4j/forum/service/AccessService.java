package ru.job4j.forum.service;

import org.springframework.stereotype.Service;
import ru.job4j.forum.model.Authority;
import ru.job4j.forum.model.User;
import ru.job4j.forum.repository.AuthorityStore;
import ru.job4j.forum.repository.UserStore;

@Service
public class AccessService {
    private UserStore userStore;
    private AuthorityStore authorityStore;

    public AccessService(UserStore userStore, AuthorityStore authorityStore) {
        this.userStore = userStore;
        this.authorityStore = authorityStore;
    }

    public void saveUser(User user) {
        userStore.save(user);
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

    public User findUserByUsername(String userName) {
        return userStore.findUserByUsername(userName);
    }

    public Authority findByAuthority(String authority) {
        return authorityStore.findByAuthority(authority);
    }
}
