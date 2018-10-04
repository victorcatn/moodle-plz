package co.edu.unal.vsacode.moodleplz.models;

import java.util.Objects;

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

    @Override
    public String toString() {
        return "KnowledgeScore{" +
                "knowledgeId='" + knowledgeId + '\'' +
                ", score=" + score +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        KnowledgeScore that = (KnowledgeScore) o;
        return score == that.score &&
                Objects.equals(knowledgeId, that.knowledgeId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(knowledgeId, score);
    }
}
