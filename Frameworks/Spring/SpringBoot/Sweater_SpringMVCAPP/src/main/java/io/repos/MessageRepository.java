package io.repos;

import io.domain.Message;
import org.springframework.data.repository.CrudRepository;

public interface MessageRepository extends CrudRepository<Message, Integer> {   // Stores all instances of (Message class/message table).

}
