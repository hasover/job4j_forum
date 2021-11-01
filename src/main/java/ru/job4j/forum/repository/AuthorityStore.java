package ru.job4j.forum.repository;

import ru.job4j.forum.model.Authority;

public interface AuthorityStore {
    Authority findByAuthority(String authority);
}
