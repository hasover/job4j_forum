package ru.job4j.forum.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import ru.job4j.forum.model.Post;

public interface PostRepository extends CrudRepository<Post, Integer>, PostStore {
    @Override
    @Query("select distinct p from Post p join fetch p.user where p.id =:pId")
    Post findById(@Param("pId") int id);
}
