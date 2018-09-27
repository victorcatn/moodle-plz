package co.edu.unal.vsacode.moodleplz.controllers;

import co.edu.unal.vsacode.moodleplz.models.Knowledge;
import co.edu.unal.vsacode.moodleplz.services.EmailService;
import co.edu.unal.vsacode.moodleplz.services.KnowledgeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/knowledges")
public class KnowledgeController {

    @Autowired
    private KnowledgeService knowledgeService;

    @Autowired
    private EmailService emailService;

    @GetMapping
    List<Knowledge> getKnowledge(){
        return knowledgeService.getKnowledge();
    }

    @GetMapping("/{id}")
    Knowledge getKnowledge(@PathVariable String id){return knowledgeService.getKnowledgeById(id);}

    @PutMapping("/{id}")
    public Knowledge updateCustomer(@PathVariable (name="id") String id, @RequestBody Knowledge newKnowledge){
        newKnowledge.setId(id);
        emailService.updatedKnowledge(newKnowledge, knowledgeService.getKnowledgeById(id));
        return knowledgeService.updateKnowledge(newKnowledge);
    }

    @PostMapping
    public Knowledge saveKnowledge(@RequestBody Knowledge newKnowledge){
        Knowledge knowledge = knowledgeService.saveKnowledge(newKnowledge);
        emailService.newKnowledge(knowledge);
        return knowledge;
    }

    @DeleteMapping("/{id}")
    public void deleteKnowledge(@PathVariable (name = "id") String id){
        emailService.deleteKnowledge(knowledgeService.getKnowledgeById(id));
        knowledgeService.deleteKnowledge(id);
    }

}
