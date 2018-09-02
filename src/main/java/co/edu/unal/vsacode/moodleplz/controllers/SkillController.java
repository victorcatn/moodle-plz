package co.edu.unal.vsacode.moodleplz.controllers;

import co.edu.unal.vsacode.moodleplz.models.Skill;
import co.edu.unal.vsacode.moodleplz.services.SkillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/skill")
public class SkillController {

    @Autowired
    private SkillService skillService;

    @GetMapping
    List<Skill> getSkill(){
        return skillService.getSkill();
    }

    @PostMapping
    public Skill saveSkill(@RequestBody Skill newSkill){
        return skillService.saveSkill(newSkill);
    }
}
