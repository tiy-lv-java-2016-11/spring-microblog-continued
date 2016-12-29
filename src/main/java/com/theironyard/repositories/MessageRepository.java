package com.theironyard.repositories;

import com.theironyard.models.Message;
import com.theironyard.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<Message, Integer> {
    List<Message> findByUser(User user);

    List<Message> findAllByOrderByCreatedOnDesc();

}
