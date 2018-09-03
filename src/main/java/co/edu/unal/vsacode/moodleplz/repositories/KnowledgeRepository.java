package co.edu.unal.vsacode.moodleplz.repositories;

import co.edu.unal.vsacode.moodleplz.models.Knowledge;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface KnowledgeRepository extends MongoRepository<Knowledge, String> {
}
