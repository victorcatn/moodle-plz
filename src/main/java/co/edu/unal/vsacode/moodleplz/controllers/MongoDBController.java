package co.edu.unal.vsacode.moodleplz.controllers;

import co.edu.unal.vsacode.moodleplz.models.StaffMember;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MongoDBController extends MongoRepository<StaffMember, String> {

}
