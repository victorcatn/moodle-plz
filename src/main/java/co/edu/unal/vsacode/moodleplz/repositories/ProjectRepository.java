package co.edu.unal.vsacode.moodleplz.repositories;


import co.edu.unal.vsacode.moodleplz.models.Project;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProjectRepository extends MongoRepository<Project, String> {
    Optional<Project> findByName(String name);
}
