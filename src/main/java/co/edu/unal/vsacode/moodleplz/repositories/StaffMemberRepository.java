package co.edu.unal.vsacode.moodleplz.repositories;

import co.edu.unal.vsacode.moodleplz.models.Knowledge;
import co.edu.unal.vsacode.moodleplz.models.Skill;
import co.edu.unal.vsacode.moodleplz.models.StaffMember;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface StaffMemberRepository extends MongoRepository<StaffMember, String> {
    StaffMember findBySkillsContaining(Skill skill);
    StaffMember findByKnowledgesContaining(Knowledge knowledge);

    @Query("{document : ?0, password : ?1}")
    Optional<StaffMember> findByPasswordAndDocument(String staffMemberDocument, String staffMemberPassword);
}
