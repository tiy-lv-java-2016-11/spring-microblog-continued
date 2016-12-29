package com.theironyard;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by darionmoore on 12/28/16.
 */
@Repository
public interface MessageRepository extends JpaRepository <Message, Integer>{

}
