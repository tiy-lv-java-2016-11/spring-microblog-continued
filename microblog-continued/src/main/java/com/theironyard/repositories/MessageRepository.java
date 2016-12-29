package com.theironyard.repositories;

import com.theironyard.models.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by sparatan117 on 12/28/16.
 */
@Repository
public interface MessageRepository extends JpaRepository<Message, Integer>{
}
