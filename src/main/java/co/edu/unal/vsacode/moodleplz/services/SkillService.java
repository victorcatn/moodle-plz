package co.edu.unal.vsacode.moodleplz.services;

import co.edu.unal.vsacode.moodleplz.models.Skill;
import co.edu.unal.vsacode.moodleplz.repositories.SkillRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SkillService {

    @Autowired
    private SkillRepository skillRepository;

    @Autowired EmailService emailService;

    public List<Skill> getSkill(){
        return skillRepository.findAll();
    }

    public Skill getSkillById(String id){
        return skillRepository.findById(id).orElse(null);
    }

    public String getSkillName(String id){
        Optional<Skill> optionalSkill =  skillRepository.findById(id);
        if(optionalSkill.isPresent()){
            Skill skill= optionalSkill.get();
            return skill.getName();
        }else{
            return "Skill not found";
        }
    }

    public Skill saveSkill(Skill newSkill){
        Skill skill = skillRepository.save(newSkill);
        new Thread(()->emailService.newSkill(newSkill)).start();
        return skill;
    }

    public Skill updateSkill(Skill newSkill){
        Skill skill = this.getSkillById(newSkill.getId());

        if(skill != null) BeanUtils.copyProperties(newSkill, skill);

        new Thread(()->emailService.updatedSkill(newSkill, skill)).start();

        return skillRepository.save(newSkill);
    }

    public void deleteSkill(String id){
        if(skillRepository.findById(id).isPresent()) {
            new Thread(()->emailService.deleteSkill(getSkillById(id))).start();
            skillRepository.delete(this.getSkillById(id));
        }
    }
}
