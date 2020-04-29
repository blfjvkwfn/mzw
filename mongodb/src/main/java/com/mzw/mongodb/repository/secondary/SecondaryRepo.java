package com.mzw.mongodb.repository.secondary;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Jonathan Meng
 * @date 16/05/2019
 */
@Repository
public interface SecondaryRepo extends MongoRepository {
}
