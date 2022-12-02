package com.academy.aegrofarm.repository;

import com.academy.aegrofarm.entity.Glebe;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface GlebeRepository extends MongoRepository<Glebe, String> {
}
