package ru.job4j.forum.repository;

import ru.job4j.forum.model.User;

public interface UserStore {

    void addNewUser(User user);

    User findUserByUsername(String username);
}
