package co.edu.unal.vsacode.moodleplz.repositories;

import co.edu.unal.vsacode.moodleplz.models.Group;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GroupRepository extends MongoRepository<Group, String> {

    @Query("{projectId : ?0}")
    Optional<Group> findByAssignedProject(String projectId);

}
