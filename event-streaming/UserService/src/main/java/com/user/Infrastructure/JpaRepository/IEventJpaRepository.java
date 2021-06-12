package com.user.Infrastructure.JpaRepository;

import com.user.Infrastructure.Entites.Event;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IEventJpaRepository extends JpaRepository<Event, Integer> {
    public List<Event> findByEntityNameAndEntityId(String entityName , String entityId );
}
