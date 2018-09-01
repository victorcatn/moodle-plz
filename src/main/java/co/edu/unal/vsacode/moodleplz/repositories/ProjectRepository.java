package co.edu.unal.vsacode.moodleplz.repositories;


import co.edu.unal.vsacode.moodleplz.models.Project;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ProjectRepository extends MongoRepository<Project, String> {
    List<Project> findByCode(String code);
}
