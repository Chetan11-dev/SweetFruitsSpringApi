package com.example.Fruits;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface FruitsRepository extends MongoRepository<Fruit, Integer> {
	
}
