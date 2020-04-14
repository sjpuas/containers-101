package com.epam.democontainers.repository;

import com.epam.democontainers.entity.PollEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PollRepository extends MongoRepository<PollEntity, String> {

}
