package co.edu.unal.vsacode.moodleplz.controllers;

import co.edu.unal.vsacode.moodleplz.models.Knowledge;
import co.edu.unal.vsacode.moodleplz.services.KnowledgeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/knowledge")
public class KnowledgeController {

    @Autowired
    private KnowledgeService knowledgeService;

    @GetMapping
    List<Knowledge> getKnowledge(){
        return knowledgeService.getKnowledge();
    }

    @PostMapping
    public Knowledge saveKwnoledge(@RequestBody Knowledge newKnowledge){
        return knowledgeService.saveKnowledge(newKnowledge);
    }

}
