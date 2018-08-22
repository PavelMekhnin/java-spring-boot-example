package kz.mekhnin.spring.headhunter.api.viewModels.response;

import java.util.ArrayList;
import java.util.List;

public class CurriculumVitaeViewModel {
    private Long id;

    private String title;

    private String summary;

    private List<EducationViewModel> educations = new ArrayList<>();

    private List<ExperienceViewModel> experiences;

    private List<SkillTagVewModel> skillTags;

    private List<LanguageSkillViewModel> languageSkills;

    private List<AwardViewModel> awards;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public List<EducationViewModel> getEducations() {
        return educations;
    }

    public void setEducations(List<EducationViewModel> educations) {
        this.educations = educations;
    }

    public List<ExperienceViewModel> getExperiences() {
        return experiences;
    }

    public void setExperiences(List<ExperienceViewModel> experiences) {
        this.experiences = experiences;
    }

    public List<SkillTagVewModel> getSkillTags() {
        return skillTags;
    }

    public void setSkillTags(List<SkillTagVewModel> skillTags) {
        this.skillTags = skillTags;
    }

    public List<LanguageSkillViewModel> getLanguageSkills() {
        return languageSkills;
    }

    public void setLanguageSkills(List<LanguageSkillViewModel> languageSkills) {
        this.languageSkills = languageSkills;
    }

    public List<AwardViewModel> getAwards() {
        return awards;
    }

    public void setAwards(List<AwardViewModel> awards) {
        this.awards = awards;
    }
}
