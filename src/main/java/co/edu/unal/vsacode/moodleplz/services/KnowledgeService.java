package co.edu.unal.vsacode.moodleplz.services;

import co.edu.unal.vsacode.moodleplz.models.Knowledge;
import co.edu.unal.vsacode.moodleplz.repositories.KnowledgeRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class KnowledgeService {

    @Autowired
    private KnowledgeRepository knowledgeRepository;

    public List<Knowledge> getKnowledge() {
        return knowledgeRepository.findAll();
    }

    public Knowledge getKnowledgeById(String id){
        return knowledgeRepository.findById(id).orElse(null);
    }

    public Knowledge saveKnowledge (Knowledge newKnowledge){
        return knowledgeRepository.save(newKnowledge);
    }

    public Knowledge updateKnowledge(Knowledge newKnowledge){
        Knowledge knowledge = this.getKnowledgeById(newKnowledge.getId());

        if (knowledge != null){
            BeanUtils.copyProperties(newKnowledge, knowledge);
        }

        return knowledgeRepository.save(newKnowledge);
    }

    public void deleteKnowledge(String id){
        knowledgeRepository.delete(this.getKnowledgeById(id));
    }
}
