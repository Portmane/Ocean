package io.repos;

import io.domain.Message;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MessageRepository extends CrudRepository<Message, Integer> {   // Stores all instances of (Message class/message table).
    List<Message> findByTag(String tag);                // Finding Messages by tag value.
}
