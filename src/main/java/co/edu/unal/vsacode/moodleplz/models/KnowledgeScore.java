package co.edu.unal.vsacode.moodleplz.models;

public class KnowledgeScore {
    private String knowledgeId;
    private int score;

    public String getKnowledgeId() {
        return knowledgeId;
    }

    public void setKnowledgeId(String knowledgeId) {
        this.knowledgeId = knowledgeId;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public KnowledgeScore(String knowledgeId, int score) {
        this.knowledgeId = knowledgeId;
        this.score = score;
    }

    public KnowledgeScore() {
    }
}
