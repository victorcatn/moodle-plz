package co.edu.unal.vsacode.moodleplz.repositories;

import co.edu.unal.vsacode.moodleplz.models.Skill;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface SkillRepository extends MongoRepository<Skill, String> {
}
