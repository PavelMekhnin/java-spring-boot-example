package kz.mekhnin.spring.headhunter.api.viewModels.response;

public class LanguageSkillViewModel {
    private Long id;

    private int grade;

    private LanguageViewModel language;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public LanguageViewModel getLanguage() {
        return language;
    }

    public void setLanguage(LanguageViewModel language) {
        this.language = language;
    }
}
