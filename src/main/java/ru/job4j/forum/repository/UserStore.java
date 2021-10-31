package ru.job4j.forum.repository;

import ru.job4j.forum.model.User;

public interface UserStore {

    User save(User user);

    User findUserByUsername(String username);
}
