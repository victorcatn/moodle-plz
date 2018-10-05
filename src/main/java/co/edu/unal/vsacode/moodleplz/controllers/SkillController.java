package co.edu.unal.vsacode.moodleplz.controllers;

import co.edu.unal.vsacode.moodleplz.models.Skill;
import co.edu.unal.vsacode.moodleplz.services.EmailService;
import co.edu.unal.vsacode.moodleplz.services.SkillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/skills")
public class SkillController {

    @Autowired
    private SkillService skillService;

    @Autowired
    private EmailService emailService;

    @GetMapping
    List<Skill> getSkills(){
        return skillService.getSkill();
    }

    @GetMapping("/{id}")
    Skill getSkill(@PathVariable String id){return skillService.getSkillById(id);}

    @PostMapping
    public Skill saveSkill(@RequestBody Skill newSkill){
        Skill skill = skillService.saveSkill(newSkill);
        emailService.newSkill(skill);
        return skill;
    }

    @PutMapping("/{id}")
    public Skill updateSkill(@PathVariable (name = "id") String id, @RequestBody Skill newSkill){
        newSkill.setId(id);
        emailService.updatedSkill(newSkill, skillService.getSkillById(id));
        return skillService.updateSkill(newSkill);
    }

    @DeleteMapping("/{id}")
    public void deleteSkill(@PathVariable (name = "id") String id){
        emailService.deleteSkill(skillService.getSkillById(id));
        skillService.deleteSkill(id);
    }
}
