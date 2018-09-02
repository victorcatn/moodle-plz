package co.edu.unal.vsacode.moodleplz.services;

import co.edu.unal.vsacode.moodleplz.models.Knowledge;
import co.edu.unal.vsacode.moodleplz.repositories.KnowledgeRepository;
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

    public Knowledge saveKnowledge (Knowledge newKnowledge){
        return knowledgeRepository.save(newKnowledge);
    }
}
