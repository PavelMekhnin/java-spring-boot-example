package kz.mekhnin.spring.headhunter.api.controllers;

import kz.mekhnin.spring.headhunter.api.mappers.modelMappers.ExperienceModelMapper;
import kz.mekhnin.spring.headhunter.api.mappers.viewModelMappers.ExperienceViewModelMapper;
import kz.mekhnin.spring.headhunter.api.viewModels.request.SaveExperienceRequest;
import kz.mekhnin.spring.headhunter.api.viewModels.response.ExperienceViewModel;
import kz.mekhnin.spring.headhunter.applicationServices.ExperienceApplicationService;
import kz.mekhnin.spring.headhunter.data.entities.Experience;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/curriculum-vitaes/{cvId}/experiences")
public class ExperiencesController {

    @Autowired
    private ExperienceApplicationService experienceApplicationService;

    @Autowired
    private ExperienceViewModelMapper experienceViewModelMapper;

    @Autowired
    private ExperienceModelMapper experienceModelMapper;

    @GetMapping
    public @ResponseBody List<ExperienceViewModel> getByCvId(@PathVariable Long cvId){
        List<Experience> experiences = experienceApplicationService.getByCvId(cvId);

        return experiences.stream().map(x -> experienceViewModelMapper.create(x)).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public @ResponseBody ExperienceViewModel getById(@PathVariable Long cvId, @PathVariable Long id){
        Experience experience = experienceApplicationService.getById(id);

        return experienceViewModelMapper.create(experience);
    }

    @PostMapping
    public @ResponseBody ExperienceViewModel add(@PathVariable Long cvId, @RequestBody SaveExperienceRequest request){
        Experience experience = new Experience();
        experienceModelMapper.Map(request, experience);

        Experience saved = experienceApplicationService.saveOrUpdate(cvId, experience);

        return experienceViewModelMapper.create(saved);
    }

    @PutMapping("/{id}")
    public @ResponseBody ExperienceViewModel update(@PathVariable Long cvId, @PathVariable Long id, @RequestBody SaveExperienceRequest request){
        Experience experience = experienceApplicationService.getById(id);
        experienceModelMapper.Map(request, experience);

        Experience saved = experienceApplicationService.saveOrUpdate(cvId, experience);

        return experienceViewModelMapper.create(saved);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        experienceApplicationService.delete(id);
    }
}
