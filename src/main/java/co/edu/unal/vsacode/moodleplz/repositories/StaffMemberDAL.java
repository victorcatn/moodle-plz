package co.edu.unal.vsacode.moodleplz.repositories;

import co.edu.unal.vsacode.moodleplz.models.KnowledgeScore;
import co.edu.unal.vsacode.moodleplz.models.SkillScore;
import co.edu.unal.vsacode.moodleplz.models.StaffMember;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

@Repository
public class StaffMemberDAL {

    private final MongoTemplate mongoTemplate;

    @Autowired
    public StaffMemberDAL(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    public StaffMember findBySatisfiedSkill(SkillScore skillScore) { //TODO change for find All for being able to select a different staff member
        Query query = new Query();

        /*query.addCriteria(Criteria.where("skills.skillId").in(skillScore.getSkillId())
                            .andOperator(Criteria.where("skills.score").gte(skillScore.getScore())));*/
        query.addCriteria(Criteria.where("skills").elemMatch(Criteria.where("skillId").is(skillScore.getSkillId())
                            .andOperator(Criteria.where("score").gte(skillScore.getScore()))));
        return mongoTemplate.findOne(query, StaffMember.class);
    }

    public StaffMember findBySatisfiedKnowledge(KnowledgeScore knowledgeScore) { //TODO change for find All for being able to select a different staff member
        Query query = new Query();

        query.addCriteria(Criteria.where("knowledges.knowledgeId").in(knowledgeScore.getKnowledgeId())
                .andOperator(Criteria.where("knowledges.score").gte(knowledgeScore.getScore())));
        return mongoTemplate.findOne(query, StaffMember.class);
    }

}
