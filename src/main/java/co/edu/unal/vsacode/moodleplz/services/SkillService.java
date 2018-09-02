package co.edu.unal.vsacode.moodleplz.services;

import co.edu.unal.vsacode.moodleplz.models.Skill;
import co.edu.unal.vsacode.moodleplz.repositories.SkillRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SkillService {

    @Autowired
    private SkillRepository skillRepository;

    public List<Skill> getSkill(){
        return skillRepository.findAll();
    }

    public Skill getSkillById(String id){
        return skillRepository.findById(id).orElse(null);
    }

    public Skill saveSkill(Skill newSkill){
        return skillRepository.save(newSkill);
    }

    public Skill updateSkill(Skill newSkill){
        Skill skill = this.getSkillById(newSkill.getId());

        if(skill != null){
            BeanUtils.copyProperties(newSkill, skill);
        }

        return skillRepository.save(newSkill);
    }

    public void deleteSkill(String id){
        skillRepository.delete(this.getSkillById(id));
    }
}
