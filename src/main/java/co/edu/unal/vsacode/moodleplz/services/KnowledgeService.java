package co.edu.unal.vsacode.moodleplz.services;

import co.edu.unal.vsacode.moodleplz.models.Knowledge;
import co.edu.unal.vsacode.moodleplz.repositories.KnowledgeRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class KnowledgeService {

    @Autowired
    private KnowledgeRepository knowledgeRepository;

    @Autowired
    private EmailService emailService;

    public List<Knowledge> getKnowledge() {
        return knowledgeRepository.findAll();
    }

    public Knowledge getKnowledgeById(String id){
        return knowledgeRepository.findById(id).orElse(null);
    }

    public String getKnowledgeName(String id){
        Optional<Knowledge> optionalKnowledge =  knowledgeRepository.findById(id);
        if(optionalKnowledge.isPresent()){
            Knowledge knowledge= optionalKnowledge.get();
            return knowledge.getName();
        }else{
            return "knowledge not found";
        }
    }

    public Knowledge saveKnowledge (Knowledge newKnowledge){
        Knowledge knowledge= knowledgeRepository.save(newKnowledge);
        new Thread(()->emailService.newKnowledge(knowledge)).start();
        return knowledge;
    }

    public Knowledge updateKnowledge(Knowledge newKnowledge){
        Knowledge oldknowledge = this.getKnowledgeById(newKnowledge.getId());
        if (oldknowledge != null) BeanUtils.copyProperties(newKnowledge, oldknowledge);
        Knowledge updatedKnowledge = knowledgeRepository.save(newKnowledge);
        new Thread(()->emailService.updatedKnowledge(updatedKnowledge, oldknowledge)).start();
        return updatedKnowledge;
    }

    public void deleteKnowledge(String id){
        if(knowledgeRepository.findById(id).isPresent()) {
            new Thread(()-> emailService.deleteKnowledge(getKnowledgeById(id))).start();
            knowledgeRepository.delete(getKnowledgeById(id));
        }
    }
}
