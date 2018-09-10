package co.edu.unal.vsacode.moodleplz;

import co.edu.unal.vsacode.moodleplz.models.Group;
import co.edu.unal.vsacode.moodleplz.models.Project;
import co.edu.unal.vsacode.moodleplz.models.Skill;
import co.edu.unal.vsacode.moodleplz.models.StaffMember;
import co.edu.unal.vsacode.moodleplz.repositories.GroupRepository;
import co.edu.unal.vsacode.moodleplz.repositories.StaffMemberRepository;
import co.edu.unal.vsacode.moodleplz.services.GroupService;
import co.edu.unal.vsacode.moodleplz.services.ProjectService;
import co.edu.unal.vsacode.moodleplz.services.StaffMemberService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Main application
 */
@SpringBootApplication
public class MoodlePlzApplication {

	public static void main(String[] args) {

		SpringApplication.run(MoodlePlzApplication.class, args);

	}
}
