package kz.mekhnin.spring.headhunter.api.mappers.viewModelMappers;

import kz.mekhnin.spring.Common.interfaces.ModelFactory;
import kz.mekhnin.spring.headhunter.api.viewModels.response.CurriculumVitaeViewModel;
import kz.mekhnin.spring.headhunter.data.entities.CurriculumVitae;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class CurriculumVitaeViewModelMapper implements ModelFactory<CurriculumVitae, CurriculumVitaeViewModel> {

    @Autowired
    private AwardViewModelMapper awardViewModelMapper;

    @Autowired
    private EducationViewModelMapper educationViewModelMapper;

    @Autowired
    private ExperienceViewModelMapper experienceViewModelMapper;

    @Autowired
    private SkillTagViewModelMapper skillTagViewModelMapper;

    @Autowired
    private LanguageSkillViewModelMapper languageSkillViewModelMapper;

    @Override
    public CurriculumVitaeViewModel create(CurriculumVitae curriculumVitae) {
        CurriculumVitaeViewModel result = new CurriculumVitaeViewModel();

        result.setId(curriculumVitae.getId());
        result.setSummary(curriculumVitae.getSummary());
        result.setTitle(curriculumVitae.getTitle());
        result.setAwards(curriculumVitae.getAwards().stream().map(x -> awardViewModelMapper.create(x)).collect(Collectors.toList()));
        result.setEducations(curriculumVitae.getEducations().stream().map(x -> educationViewModelMapper.create(x)).collect(Collectors.toList()));
        result.setExperiences(curriculumVitae.getExperiences().stream().map(x -> experienceViewModelMapper.create(x)).collect(Collectors.toList()));
        //result.setLanguageSkills(curriculumVitae.getLanguageSkills().stream().map(x -> languageSkillViewModelMapper.create(x)).collect(Collectors.toList()));
        //result.setSkillTags(curriculumVitae.getSkillTags().stream().map(x -> skillTagViewModelMapper.create(x)).collect(Collectors.toList()));

        return result;
    }
}
