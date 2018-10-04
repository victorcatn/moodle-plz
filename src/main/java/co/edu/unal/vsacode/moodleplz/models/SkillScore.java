package co.edu.unal.vsacode.moodleplz.models;

import java.util.Objects;

public class SkillScore {
    private String skillId;
    private int score;

    public String getSkillId() {
        return skillId;
    }

    public void setSkillId(String skillId) {
        this.skillId = skillId;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public SkillScore(String skillId, int score) {
        this.skillId = skillId;
        this.score = score;
    }

    public SkillScore() {
    }


    @Override
    public String toString() {
        return "SkillScore{" +
                "skillId='" + skillId + '\'' +
                ", score=" + score +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SkillScore that = (SkillScore) o;
        return score == that.score &&
                Objects.equals(skillId, that.skillId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(skillId, score);
    }
}
