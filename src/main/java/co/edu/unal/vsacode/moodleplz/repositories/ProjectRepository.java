package co.edu.unal.vsacode.moodleplz.repositories;


import co.edu.unal.vsacode.moodleplz.models.Project;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface ProjectRepository extends MongoRepository<Project, String> {
    Optional<Project> findByName(String name);
}
