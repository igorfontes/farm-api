package com.academy.aegrofarm.repository;

import com.academy.aegrofarm.entity.Farm;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface FarmRepository extends MongoRepository<Farm, String> {
}
