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

@SpringBootApplication
public class MoodlePlzApplication {

	public static void main(String[] args) {

		SpringApplication.run(MoodlePlzApplication.class, args);




	}
	@Bean
	CommandLineRunner init(GroupService groupService,
						   GroupRepository groupRepository,
						   StaffMemberService staffMemberService,
						   ProjectService projectService
						   ) {

		return args -> {

			Skill java = new Skill("Java");
			Skill php = new Skill("php");

			List<Skill> skills = new ArrayList<>();
			skills.add(java);
			skills.add(php);

			Project project = new Project("moodle-plz",new Date(),skills,null);

			/*projectService.saveProject(project);

			StaffMember juan = new StaffMember("10","1","1","pancho","perez");
			juan.addSkill(new Skill("php"));
			staffMemberService.saveStaffMember(juan);

			Group gp = groupService.generateGroup(project);*/

			//System.out.println(gp.getMembersId());


		};

	}
}
