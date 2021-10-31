package ru.job4j.forum.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import ru.job4j.forum.model.Topic;

public interface TopicRepository extends CrudRepository<Topic, Integer>, TopicStore {
    @Override
    @Query("select distinct t from Topic t join fetch t.user where t.id =:tId")
    Topic findById(@Param("tId") int id);
}
