package com.academy.aegrofarm.repository;

import com.academy.aegrofarm.entity.Production;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductionRepository extends MongoRepository<Production, String> {
}
