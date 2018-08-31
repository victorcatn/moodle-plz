package co.edu.unal.vsacode.moodleplz.repositories;

import co.edu.unal.vsacode.moodleplz.models.StaffMember;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface StaffMemberRepository extends MongoRepository<StaffMember, String> {

}
